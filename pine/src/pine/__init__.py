import typer


from pine.utils import init as pine_init
from pine.gen_routes import main as gen_routes
from pine.gen_components import main as gen_components
from pine.gen_manifest import main as gen_manifest


app = typer.Typer()


@app.command()
def init():
    pine_init()

@app.command()
def manifest():
    gen_manifest()


@app.command()
def components():
    gen_components()


@app.command()
def routes():
    gen_routes()


if __name__ == "__main__":
    app()
