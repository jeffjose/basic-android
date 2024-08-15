#!/usr/bin/env python

import glob
from pathlib import Path

from patterns import import_pattern

from utils import get_screen_path, read_file, write_file

INPUT_PATTERN = 'src/routes/**/*.pine'
TEMPLATE = '''

%%IMPORT%%

@Composable
fun %%NAME%%Screen(navController: NavHostController, modifier: Modifier = Modifier) {
    %%CONTENT%%
}

@Preview
@Composable
fun %%NAME%%ScreenPreview() {
    CupcakeTheme {
        %%NAME%%Screen(
                navController = rememberNavController(),
                modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

'''

def collect_files():

    files = [Path(x) for x in glob.glob(INPUT_PATTERN)]
    print(files)

    return files


def get_template():

    return TEMPLATE



def get_slug(file):

    return file.parent.name.capitalize()

def parse(data):
    lines = data.split('\n')

    imports = []
    contents = []

    for line in lines:
        if import_pattern.match(line):
            imports.append(line)
        else:
            contents.append(line)

    return {'imports': '\n'.join(imports), 'contents': '\n'.join(contents)}

def create_screen(template, file):

    parcel = parse(read_file(file))

    slug = get_slug(file)

    final = template.replace('%%IMPORT%%', parcel['imports']).replace("%%CONTENT%%", parcel['contents']).replace("%%NAME%%", slug)

    write_file(get_screen_path() / Path(slug + '.kt'), final)


def main():
    files = collect_files()

    template = get_template()

    for file in files:
        create_screen(template, file)


if __name__ == "__main__":
    main()
