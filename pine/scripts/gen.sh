#!/usr/bin/bash

echo "--> Pine"

COPY="cp"

# 0. setup
rye run --pyproject pine/pyproject.toml python pine/init.py

# 1. routes/
rye run --pyproject pine/pyproject.toml python pine/gen_routes.py

# 1a. components/
rye run --pyproject pine/pyproject.toml python pine/gen_components.py

# 2. src/main/AndroidManifest.xml
rye run --pyproject pine/pyproject.toml python pine/gen_manifest.py

#  3. src/main/res/drawable*
mkdir -p dist/cupcake/app/src/main/res
$COPY -r pine/_template/app/src/main/res/drawable* dist/cupcake/app/src/main/res/

#  4. src/main/res/mipmap*
mkdir -p dist/cupcake/app/src/main/res
$COPY -r pine/_template/app/src/main/res/mipmap* dist/cupcake/app/src/main/res/

#  5. src/main/res/values/dimens.xml
mkdir -p dist/cupcake/app/src/main/res/values
$COPY pine/_template/app/src/main/res/values/dimens.xml dist/cupcake/app/src/main/res/values/dimens.xml

#  6. src/main/res/values/strings.xml
mkdir -p dist/cupcake/app/src/main/res/values
$COPY pine/_template/app/src/main/res/values/strings.xml dist/cupcake/app/src/main/res/values/strings.xml

#  7. src/main/res/values/themes.xml
mkdir -p dist/cupcake/app/src/main/res/values
$COPY pine/_template/app/src/main/res/values/themes.xml dist/cupcake/app/src/main/res/values/themes.xml

#  8. app/build.gradle.kts
$COPY pine/_template/app/build.gradle.kts dist/cupcake/app/build.gradle.kts

#  9. app/proguard-rules.pro
$COPY pine/_template/app/proguard-rules.pro dist/cupcake/app/proguard-rules.pro

# 10. build.gradle.kts
$COPY pine/_template/build.gradle.kts dist/cupcake/build.gradle.kts

# 11. settings.gradle.kts
$COPY pine/_template/settings.gradle.kts dist/cupcake/settings.gradle.kts

# 12. gradle*
$COPY -r pine/_template/gradle* dist/cupcake/

# 13. app/src/main/java/com/example/cupcake/ui/theme/
mkdir -p dist/cupcake/app/src/main/java/com/example/cupcake/ui/
$COPY -r pine/_template/app/src/main/java/com/example/cupcake/ui/theme dist/cupcake/app/src/main/java/com/example/cupcake/ui/

# 14. src/CupcakeScreen.kt
#cp -rv pine/_template/app/src/main/java/com/example/cupcake/CupcakeScreen.kt dist/cupcake/app/src/main/java/com/example/cupcake/
$COPY -r src/CupcakeScreen.kt dist/cupcake/app/src/main/java/com/example/cupcake/

# 15. src/MainActivity.kt
$COPY -r pine/_template/app/src/main/java/com/example/cupcake/MainActivity.kt dist/cupcake/app/src/main/java/com/example/cupcake/
