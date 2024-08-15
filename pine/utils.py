import shutil
from pathlib import Path

OUTPUT_BASE = 'dist'

def get_project_name():
    return 'cupcake'

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

def get_output_dir():
    return Path(OUTPUT_BASE) / get_project_name()

def get_app_dir():
    return get_output_dir() / 'java/com/example' / get_project_name()

def get_screen_path():

    app_dir = get_app_dir()

    ui_dir = app_dir / 'ui'

    mkdir(ui_dir)

    return ui_dir
