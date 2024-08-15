#!/usr/bin/bash

echo "--> Pine"

# 0. setup
rye run --pyproject pine/pyproject.toml python pine/init.py

# 1. routes/
rye run --pyproject pine/pyproject.toml python pine/gen_routes.py

# 2. src/main/AndroidManifest.xml
rye run --pyproject pine/pyproject.toml python pine/gen_manifest.py

#  3. src/main/res/drawable*
#  4. src/main/res/mipmap*
#  5. src/main/res/values/dimens.xml
#  6. src/main/res/values/strings.xml
#  7. src/main/res/values/themes.xml
#  8. app/build.gradle.kts
#  9. app/proguard-rules.kts
# 10. app/proguard-rules.kts
# 11. build.gradle.kts
# 12. settings.gradle.kts
# 13. gradle*
