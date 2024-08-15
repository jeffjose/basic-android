#!/usr/bin/env python

import glob
from path import Path

TEMPLATE = "pine/routes/screen.kt.template"
OUTPUT_DIR = "src/ui"


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
    return file.write_lines(lines)

def get_slug(file):

    return file.parent.basename().capitalize()

def create_screen(template, file):

    contents = _read_file(file)

    slug = get_slug(file)

    final = template.replace("%%CONTENT%%", contents).replace("%%NAME%%", slug)

    _write_file(Path(slug + '.kt'), final)


def main():
    files = collect_files()

    template = get_template()

    create_screen(template, files[0])


if __name__ == "__main__":
    main()
