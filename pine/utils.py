import ujson as json
import shutil
from pathlib import Path


#########
# Project paths


OUTPUT_BASE = 'dist'

def get_project_name():
    return 'cupcake'


def get_output_dir():
    return Path(OUTPUT_BASE) / get_project_name()

def get_top_app_dir():
    return get_output_dir() / 'app'

def get_src_dir():
    return get_top_app_dir() / 'src'

def get_main_dir():
    return get_src_dir() / 'main'

def get_app_dir():
    return get_main_dir()/ 'java/com/example' / get_project_name()

def get_screen_path():

    app_dir = get_app_dir()

    ui_dir = app_dir / 'ui'

    mkdir(ui_dir)

    return ui_dir

##############
def get_project_name_capitalized():
    return get_project_name().capitalize()

def mkdir(path):

    print('mkdir ', path)
    path.mkdir(exist_ok = True, parents = True)

def clean(path):
    print('Removing path', path)
    shutil.rmtree(path, ignore_errors=True)

def init():

    app_dir = get_app_dir()

    clean(app_dir)
    mkdir(app_dir)



def read_file(file: Path):
    return file.read_text()


def write_file(file, lines):
    print('Writing ', file)
    file.write_text(lines)


def read_json(file: str):
    return json.load(open(file, 'r'))


def write_json(file: str, obj: any):
    return json.dump(obj, open(file, 'w'))
