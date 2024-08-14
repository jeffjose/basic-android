#!/usr/bin/env python

import glob

TEMPLATE = "pine_screen.kt.template"
OUTPUT_DIR = "../ui"


def collect_files():

    files = glob.glob("*.pine")
    print(files)

    return files


def get_template():

    lines = _read_file(TEMPLATE)

    return lines


def _read_file(file):
    return open(file, "r").read()


def _write_file(file, lines):
    fp = open(file, "w")
    return fp.writelines(lines)


def create_screen(template, file):

    contents = _read_file(file)

    slug = file.replace(".pine", "").capitalize()

    final = template.replace("%%CONTENT%%", contents).replace("%%NAME%%", slug)

    _write_file(slug + '.kt', final)


def main():
    files = collect_files()

    template = get_template()

    create_screen(template, files[0])


if __name__ == "__main__":
    main()
