---
title: Installation
nav_order: 3
---

# Installation

## Using the FRC Checkmate Installer

FRC Checkmate comes with an installer that automatically builds and installs the source code to your FRC robot's code.

_The installer requires Python 3 to run._

To use it, run the following command in the root of your codebase:

### Windows (PowerShell)

```powershell
python - <<'PY'
Invoke-Expression (Invoke-WebRequest https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/install.py).Content
PY
```

### macOS/Linux (sh)

```sh
python3 <(curl -sS https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/install.py)
```

### Manual

If you can't/don't want to use the Python-based installer, download
the [Checkmate.java from logandhillon/frc-checkmate](https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/src/main/java/com/logandhillon/frc_checkmate/Checkmate.java),
and add it to your project's utils folder.

🔗 [logandhillon/frc-checkmate/main/src/main/java/.../Checkmate.java](https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/src/main/java/com/logandhillon/frc_checkmate/Checkmate.java)