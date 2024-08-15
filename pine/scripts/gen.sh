#!/usr/bin/bash

echo "--> Pine"

rye run --pyproject pine/pyproject.toml python pine/init.py
rye run --pyproject pine/pyproject.toml python pine/gen_routes.py
rye run --pyproject pine/pyproject.toml python pine/gen_manifest.py
