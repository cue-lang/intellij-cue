<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# IntelliJ CUE Language Support Changelog

## [Unreleased]

### Added

### Changed

### Fixed

## 0.12.0

### Added

- Experimental integration with CUE LSP, based on JetBrains' LSP support.

## 0.11.0

### Changed

- Dropped support for IDEs earlier than 2024.2.

## 0.10.0

### Added

- Support required field constraints for Cue 0.6.0.
- Support field constraints for dynamic fields.
- Color scheme settings for field constraints. The highlighting of "optional field / required field" is merged with the rendering of "regular field / dynamic field".

## 0.9.0

### Added

- Structure view for Cue files, [contributed](https://github.com/monogon-dev/intellij-cue/pull/63) by [borissmidt](https://github.com/borissmidt).

## 0.8.8

### Added

- [#53](https://github.com/monogon-dev/intellij-cue/issues/53): Support dynamic fields

## 0.8.7

### Changed

- Support 2023.1 EAP
- Drop support for 2021.1, 2021.2 and 2021.3

## 0.8.6

### Fixed

- Support syntax of alias expressions

## 0.8.5

### Fixed

- #54: Error reported when using an alias

## 0.8.4

### Fixed

- Compatibility with 2022.1

## 0.8.3

### Fixed

- Compatibility with 2021.3

## 0.8.2

### Fixed

- Compatibility with 2021.2

## 0.8.1

### Added

- Automatically indent inside of `{}`, `[]`, and `()`.
- Code style settings to configure the behaviour of TAB handling.
- Default to TAB indents (two characters wide).
- Fix indent handling when pressing enter between `[]`.

## 0.8.0

### Fixed

- [#35](https://github.com/monogon-dev/intellij-cue/issues/35): Trailing bracket on ellipsis multi-line list not parsed correctly.
- [#36](https://github.com/monogon-dev/intellij-cue/issues/36): Don't accept implicit comma separator in list literals.

## 0.6.0

- Added: Automatically insert closing quotes when typing the opening quote, available for simple and multiline literals.
- Added: Support folding for CUE elements (file header, imports, import groups, structs, lists, interpolations, multiline strings,
  attributes).
- Added: Settings to control the default folding state of CUE elements.
- Fix [#27](https://github.com/monogon-dev/intellij-cue/issues/27), file starting with attribute raised as invalid
- Added: Highlighting setting for attributes.

## 0.5.0

- Support variable escape prefix of the language specification.
- Support commenting lines via shortcut or action.
- Support highlighting of brace pairs in the editor.
- Fix parsing of function calls in interpolations.

## 0.4.0

- Color scheme settings to configure CUE highlighting
- Added Highlighting setting for optional field names

## 0.3.1

- Improved support for the CUE grammar
- Improved highlighting

## 0.2.1

- First public release!
- Basic support for parsing and syntax highlighting.
