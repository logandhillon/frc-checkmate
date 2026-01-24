---
title: Installation
nav_order: 3
---

# Installation

## Using the FRC Checkmate Installer

FRC Checkmate comes with an installer that automatically builds and installs the source code to your FRC robot's code.

To use it, run the following command:

### macOS/Linux (sh)

```sh
python3 <(curl -sS https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/install.py)
```

### Windows (PowerShell)

```pwsh
python - <<'PY'
Invoke-Expression (Invoke-WebRequest https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/install.py).Content
PY
```

The installer requires Python 3 to run.
