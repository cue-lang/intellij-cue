package dev.monogon.cue.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.components.JBTextField;
import dev.monogon.cue.Messages;

import javax.swing.*;

public class CueSettingsForm {
    private JPanel mainPanel;
    private JTextField cuePathInput;

    @SuppressWarnings("DialogTitleCapitalization")
    private void createUIComponents() {
        var cuePathBrowseField = new TextFieldWithBrowseButton();
        cuePathBrowseField.addBrowseFolderListener(Messages.get("applicationSettings.cuePath.dialogTitle"),
                                                   Messages.get("applicationSettings.cuePath.dialogLabel"),
                                                   null, FileChooserDescriptorFactory.createSingleLocalFileDescriptor());
        cuePathInput = cuePathBrowseField.getTextField();
        if (cuePathInput instanceof JBTextField) {
            ((JBTextField)cuePathInput).getEmptyText().setText(Messages.get("applicationSettings.cuePath.emptyText"));
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void applyFrom(CueLocalSettings settings) {
        cuePathInput.setText(StringUtil.defaultIfEmpty(settings.getCueExecutablePath(), ""));
    }

    public void applyTo(CueLocalSettings settings) {
        settings.setCueExecutablePath(cuePathInput.getText());
    }
}
