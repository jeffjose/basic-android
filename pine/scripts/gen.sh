#!/usr/bin/bash

echo "--> Pine"

# 0. setup
rye run --pyproject pine/pyproject.toml python pine/init.py

# 1. routes/
rye run --pyproject pine/pyproject.toml python pine/gen_routes.py

# 2. src/main/AndroidManifest.xml
rye run --pyproject pine/pyproject.toml python pine/gen_manifest.py

#  3. src/main/res/drawable*
mkdir -p dist/cupcake/app/src/main/res
cp -rv pine/_template/app/src/main/res/drawable* dist/cupcake/app/src/main/res/

#  4. src/main/res/mipmap*
mkdir -p dist/cupcake/app/src/main/res
cp -rv pine/_template/app/src/main/res/mipmap* dist/cupcake/app/src/main/res/

#  5. src/main/res/values/dimens.xml
mkdir -p dist/cupcake/app/src/main/res/values
cp -v pine/_template/app/src/main/res/values/dimens.xml dist/cupcake/app/src/main/res/values/dimens.xml

#  6. src/main/res/values/strings.xml
mkdir -p dist/cupcake/app/src/main/res/values
cp -v pine/_template/app/src/main/res/values/strings.xml dist/cupcake/app/src/main/res/values/strings.xml

#  7. src/main/res/values/themes.xml
mkdir -p dist/cupcake/app/src/main/res/values
cp -v pine/_template/app/src/main/res/values/themes.xml dist/cupcake/app/src/main/res/values/themes.xml

#  8. app/build.gradle.kts
cp -v pine/_template/app/build.gradle.kts dist/cupcake/app/build.gradle.kts

#  9. app/proguard-rules.kts
cp -v pine/_template/app/proguard-rules.kts dist/cupcake/app/proguard-rules.kts

# 10. build.gradle.kts
cp -v pine/_template/build.gradle.kts dist/cupcake/build.gradle.kts

# 11. settings.gradle.kts
cp -v pine/_template/settings.gradle.kts dist/cupcake/settings.gradle.kts

# 12. gradle*
cp -rv pine/_template/gradle* dist/cupcake/

# 13. app/src/main/java/com/example/cupcake/ui/theme/
mkdir -p dist/cupcake/app/src/main/main/java/com/example/cupcacke/ui
cp -rv pine/_template/app/src/main/java/com/example/cupcake/ui/theme dist/cupcake/app/src/main/java/com/example/cupcake/ui/

# 14. src/CupcakeScreen.kt
cp -rv pine/_template/app/src/main/java/com/example/cupcake/CupcakeScreen.kt dist/cupcake/app/src/main/java/com/example/cupcake/

# 15. src/MainActivity.kt
cp -rv pine/_template/app/src/main/java/com/example/cupcake/MainActivity.kt dist/cupcake/app/src/main/java/com/example/cupcake/
