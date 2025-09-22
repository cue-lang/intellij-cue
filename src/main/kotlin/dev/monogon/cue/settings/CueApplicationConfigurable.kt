package dev.monogon.cue.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.observable.properties.ObservableMutableProperty
import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.openapi.observable.util.bind
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.UnscaledGaps
import com.intellij.util.text.nullize
import com.intellij.util.ui.ComponentWithEmptyText
import dev.monogon.cue.Messages
import dev.monogon.cue.cli.CueCommandService

class CueApplicationConfigurable : BoundConfigurable(Messages.get("applicationSettings.displayName")) {
    private val initialLspEnabledState: Boolean = CueLocalSettingsService.getSettings().isLspEnabled

    @Suppress("DialogTitleCapitalization", "UnstableApiUsage")
    override fun createPanel(): DialogPanel {
        return panel {
            val graph = PropertyGraph()
            val cuePathLabel = graph.property(cueBinaryPathMessage(CueLocalSettingsService.getSettings().cueExecutablePath, true) ?: "")

            row(Messages.get("applicationSettings.cuePath.label")) {
                val cueFileDescriptor = FileChooserDescriptorFactory
                    .createSingleLocalFileDescriptor()
                    .withTitle(Messages.get("applicationSettings.cuePath.dialogTitle"))

                textFieldWithBrowseButton(cueFileDescriptor)
                    .align(AlignX.FILL)
                    .applyToComponent {
                        (textField as? ComponentWithEmptyText)?.emptyText?.text = Messages.get("applicationSettings.cuePath.emptyText")
                    }
                    .bindText(
                        { CueLocalSettingsService.getSettings().cueExecutablePath ?: "" },
                        { CueLocalSettingsService.getSettings().cueExecutablePath = it.nullize(true) }
                    )
                    .onChanged { updateCueBinaryStatusLabel(cuePathLabel, it.text.nullize(true)) }
                    .validationInfo {
                        val error = cueBinaryPathMessage(it.text.nullize(true), withSuccessMessage = false)
                        if (error != null) error(error) else null
                    }.customize(UnscaledGaps(bottom = 3))
            }

            // comment in a new row to display the status of the CUE binary
            row("") { // empty label to enforce indentation of the comment
                comment("")
                    .align(AlignX.FILL)
                    .customize(UnscaledGaps(top = 0))
                    .applyToComponent { bind(cuePathLabel) }
            }

            row {
                checkBox(Messages.get("applicationSettings.lspSupport.label")).bindSelected(
                    { CueLocalSettingsService.getSettings().isLspEnabled },
                    { CueLocalSettingsService.getSettings().isLspEnabled = it }
                ).enabled(CueLspSupport.isLspSupportAvailable())

                if (!CueLspSupport.isLspSupportAvailable()) {
                    rowComment(Messages.get("applicationSettings.lspSupport.unavailableLSPComment"))
                }
            }
        }
    }

    override fun disposeUIResources() {
        val newEnableLspValue = CueLocalSettingsService.getSettings().isLspEnabled
        if (newEnableLspValue != initialLspEnabledState) {
            ApplicationManager.getApplication().messageBus.syncPublisher(CueSettingsListener.TOPIC).lspStateChanged(newEnableLspValue)
        }
    }

    private fun updateCueBinaryStatusLabel(target: ObservableMutableProperty<String>, userPath: String?) {
        target.set(cueBinaryPathMessage(userPath, withSuccessMessage = true) ?: "")
    }

    private fun cueBinaryPathMessage(userPath: String?, withSuccessMessage: Boolean): String? {
        val validatedUserPath = CueCommandService.getInstance().findCueBinaryPath(userPath)
        return when {
            // unavailable in $PATH
            validatedUserPath == null && userPath == null -> Messages.get("applicationSettings.cuePath.detection.notFound")
            // user path is invalid
            validatedUserPath == null -> Messages.get("applicationSettings.cuePath.detection.invalidPath", userPath!!)
            // found in $PATH and success message requested
            withSuccessMessage -> Messages.get("applicationSettings.cuePath.detection.validPath", validatedUserPath)
            else -> null
        }
    }
}