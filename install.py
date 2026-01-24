"""Installer for FRC Checkmate"""

from sys import exit
import urllib.request
import os

RESET = "\033[0m"
BOLD = "\033[1m"
RED = "\033[31m"
GREEN = "\033[32m"
YELLOW = "\033[33m"
CYAN = "\033[36m"

URL = "https://raw.githubusercontent.com/logandhillon/frc-checkmate/main/src/main/java/com/logandhillon/frc_checkmate/Checkmate.java"
DEFAULT_DIR = os.path.join("src", "main", "java", "frc", "robot", "utils")


def abort():
    print(f"{RED}Aborting!{RESET}")
    exit(0)


if __name__ == "__main__":
    print(f"""{CYAN}{BOLD}========================================
FRC Checkmate Installer [v1.0]
(C) 2026 Logan Dhillon
========================================{RESET}
""")

    with urllib.request.urlopen(urllib.request.Request(
        URL,
        headers={
            "Accept": "application/vnd.github+json",
            "User-Agent": "logandhillon/frc-checkmate-installer/1.0"
        }
    )) as response:
        source = response.read().decode("utf-8")

    target_dir = DEFAULT_DIR
    confirm_install = input(
        f"{YELLOW}Install FRC Checkmate to '{target_dir}'? [y/N]{RESET} ").strip().lower()
    if confirm_install != "y":
        abort()

    os.makedirs(target_dir, exist_ok=True)

    target_file = os.path.join(target_dir, "Checkmate.java")

    if os.path.exists(target_file):
        confirm_overwrite = input(
            f"{YELLOW}Warning:{RESET} File '{target_file}' already exists. Overwrite? [y/N] ").strip().lower()
        if confirm_overwrite != "y":
            abort()

    with open(target_file, "w", encoding="utf-8") as f:
        f.write(source)

    print(f"{GREEN}✔ FRC Checkmate installed to {target_file}{RESET}")
