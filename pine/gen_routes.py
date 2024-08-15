#!/usr/bin/env python

import glob
from pathlib import Path

from utils import get_screen_path

TEMPLATE = "pine/routes/screen.kt.template"

def collect_files():

    files = [Path(x) for x in glob.glob("src/routes/**/*.pine")]
    print(files)

    return files


def get_template():

    lines = _read_file(Path(TEMPLATE))

    return lines


def _read_file(file: Path):
    return file.read_text()


def _write_file(file, lines):
    print('Writing ', file)
    file.write_text(lines)

def get_slug(file):

    return file.parent.name.capitalize()

def create_screen(template, file):

    contents = _read_file(file)

    slug = get_slug(file)

    final = template.replace("%%CONTENT%%", contents).replace("%%NAME%%", slug)

    _write_file(get_screen_path() / Path(slug + '.kt'), final)


def main():
    files = collect_files()

    template = get_template()

    for file in files:
        create_screen(template, file)


if __name__ == "__main__":
    main()
