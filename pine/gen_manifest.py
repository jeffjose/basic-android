#!/usr/bin/env python

import glob
from pathlib import Path
import ujson as json

from utils import get_screen_dir, read_file, write_file, get_main_dir, write_json, read_json, get_project_name_capitalized

INPUT_MANIFEST = 'src/manifest.json'
OUTPUT_MANIFEST = get_main_dir() / 'AndroidManifest.xml'

TEMPLATE = '''<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    %%PERMISSIONS%%

    <application
        android:icon="{icon}"
        android:label="{label}"
        android:roundIcon="{roundIcon}"
        android:supportsRtl="{supportsRtl}"
        android:theme="{theme}"
        tools:targetApi="{targetApi}">
        <activity
            android:name=".{activity}"
            android:exported="{exported}"
            android:theme="{theme}">
            <intent-filter>
                <action android:name="{intentAction}" />
                <category android:name="{intentCategory}" />
            </intent-filter>
        </activity>
    </application>

</manifest>
'''

TEMPLATE_PERMISSION = '''
<uses-permission android:name="{permission}" />
'''

def get_template():

    return TEMPLATE


def _handle_label(text):

    if text.startswith('@string/'):
        return text
    else:
        raise ValueError('label is not of the format: @string/foobar')

def _handle_icon(text):
    if text.startswith('@mipmap/'):
        return text
    else:
        raise ValueError('icon is not of the format: @mipmap/foobar')


def _handle_roundIcon(text):
    if text.startswith('@mipmap/'):
        return text
    else:
        raise ValueError('icon is not of the format: @mipmap/foobar')

def _handle_supportsRtl(text):
    return  text

def _handle_theme(text):
    if text.startswith('@style/'):
        return text
    else:
        raise ValueError('theme is not of the format: @style/foobar')

def _handle_targetApi(text):

    return text
    

def _handle_activity(text):
    return text

def _handle_exported(text):
    return text

def _handle_intentAction(text):
    return text

def _handle_intentCategory(text):
    return text

def _handle_permissions(permissions):
    return permissions

def parse(data):

    return {
        'label':          _handle_label(data.get('label', '@string/app_name')),
        'icon':           _handle_icon(data.get('icon', '@mipmap/ic_launcher')),
        'roundIcon':      _handle_roundIcon(data.get('roundIcon', '@mipmap/ic_launcher_round')),
        'supportsRtl':    _handle_supportsRtl(data.get('supportsRtl', 'true')),
        'theme':          _handle_theme(data.get('theme', f'@style/Theme.{get_project_name_capitalized()}')),
        'targetApi':      _handle_targetApi(data.get('targetApi')),
        'activity':       _handle_activity(data.get('activity', 'MainActivity')),
        'exported':       _handle_exported(data.get('exported', 'true')),
        'intentAction':   _handle_intentAction(data.get('intent', {}).get('action')),
        'intentCategory': _handle_intentCategory(data.get('intent', {}).get('category')),
        'permissions':    _handle_permissions(data.get('permissions', []))
        
    }

def create_manifest(template, manifest):

    parcel = parse(manifest)

    final = template.format(**parcel).strip()

    permission_lines = []
    for permission in parcel['permissions']:
        permission_lines.append(TEMPLATE_PERMISSION.format(permission = permission))

    final = final.replace('%%PERMISSIONS%%', "\n".join(permission_lines))

    write_file(OUTPUT_MANIFEST, final)


def get_manifest():

    return read_json(INPUT_MANIFEST)

def main():

    manifest = get_manifest()

    template = get_template()

    create_manifest(template, manifest)


if __name__ == "__main__":
    main()
