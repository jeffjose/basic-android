from pine.compiler.parser import (
    expand_component_line,
    get_var_declarations,
    get_exports,
    get_frontmatter,
)
from pytest_cases import parametrize_with_cases, parametrize


def _clean_lines(lines):
    return [x for x in lines if x.strip()]


@parametrize(
    vdef=[
        "external var foo: Int",
        "external var foo:Int",
        "external var foo      :         Int",
        "external var foo: String",
        "external var foo",
        "external var foo: Int = 'bar'",
        "external var foo:Int='bar'",
        "external var foo      :         Int   = 'bar'",
        "external var foo: String = 'bar'",
        "external var foo='bar'",
    ]
)
def case_expand_component_line_external_var(vdef):
    return (
        vdef,
        "var foo by rememberSaveable(inputs=arrayOf(foo)) { mutableStateOf(foo) }",
    )


@parametrize(
    vdef=[
        "external val foo: Int",
        "external val foo:Int",
        "external val foo      :         Int",
        "external val foo: String",
        "external val foo",
        "external val foo: Int = 'bar'",
        "external val foo:Int='bar'",
        "external val foo      :         Int   = 'bar'",
        "external val foo: String = 'bar'",
        "external val foo='bar'",
    ]
)
def case_expand_component_line_external_val(vdef):
    return (
        vdef,
        "val foo by rememberSaveable(inputs=arrayOf(foo)) { mutableStateOf(foo) }",
    )


@parametrize(
    vdef=[
        "var #foo = 'bar'",
        "var #foo='bar'",
        "var #foo        =          'bar'",
    ]
)
def case_expand_component_line_var_remember(vdef):
    return (
        vdef,
        "var foo by remember { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "var #foo    :    String       =          'bar'",
        "var #foo:String='bar'",
        "var #foo : String = 'bar'",
    ]
)
def case_expand_component_line_var_remember_with_type(vdef):
    return (
        vdef,
        "var foo : String by remember { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "val #foo = 'bar'",
        "val #foo='bar'",
        "val #foo        =          'bar'",
    ]
)
def case_expand_component_line_val_remember(vdef):
    return (
        vdef,
        "val foo by remember { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "val #foo    :    String       =          'bar'",
        "val #foo:String='bar'",
        "val #foo : String = 'bar'",
    ]
)
def case_expand_component_line_val_remember_with_type(vdef):
    return (
        vdef,
        "val foo : String by remember { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "var $foo = 'bar'",
        "var $foo='bar'",
        "var $foo        =          'bar'",
    ]
)
def case_expand_component_line_var_mutable(vdef):
    return (
        vdef,
        "var foo = mutableStateOf('bar')",
    )


@parametrize(
    vdef=[
        "var $foo    :    String       =          'bar'",
        "var $foo:String='bar'",
        "var $foo : String = 'bar'",
    ]
)
def case_expand_component_line_var_mutable_with_type(vdef):
    return (
        vdef,
        "var foo = mutableStateOf<String>('bar')",
    )


@parametrize(
    vdef=[
        "val $foo = 'bar'",
        "val $foo='bar'",
        "val $foo        =          'bar'",
    ]
)
def case_expand_component_line_val_mutable(vdef):
    return (
        vdef,
        "val foo = mutableStateOf('bar')",
    )


@parametrize(
    vdef=[
        "val $foo    :    String       =          'bar'",
        "val $foo:String='bar'",
        "val $foo : String = 'bar'",
    ]
)
def case_expand_component_line_val_mutable_with_type(vdef):
    return (
        vdef,
        "val foo = mutableStateOf<String>('bar')",
    )


@parametrize(
    vdef=[
        "var *foo = 'bar'",
        "var *foo='bar'",
        "var *foo        =          'bar'",
    ]
)
def case_expand_component_line_var_rememberSaveable(vdef):
    return (
        vdef,
        "var foo by rememberSaveable { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "var *foo    :    String       =          'bar'",
        "var *foo:String='bar'",
        "var *foo : String = 'bar'",
    ]
)
def case_expand_component_line_var_rememberSaveable_with_type(vdef):
    return (
        vdef,
        "var foo : String by rememberSaveable { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "val *foo = 'bar'",
        "val *foo='bar'",
        "val *foo        =          'bar'",
    ]
)
def case_expand_component_line_val_rememberSaveable(vdef):
    return (
        vdef,
        "val foo by rememberSaveable { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "val *foo    :    String       =          'bar'",
        "val *foo:String='bar'",
        "val *foo : String = 'bar'",
    ]
)
def case_expand_component_line_val_rememberSaveable_with_type(vdef):
    return (
        vdef,
        "val foo : String by rememberSaveable { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "var *text(stateSaver=TextFieldValue.Saver) = 'bar'",
        "var       *text(stateSaver=TextFieldValue.Saver)     =    'bar'",
        "var       *text(   stateSaver=TextFieldValue.Saver   )     =    'bar'",
    ]
)
def case_expand_component_line_val_rememberSaveable_with_saver(vdef):
    return (
        vdef,
        "var text by rememberSaveable(stateSaver=TextFieldValue.Saver) { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "var #text(stateSaver=TextFieldValue.Saver) = 'bar'",
        "var       #text(stateSaver=TextFieldValue.Saver)     =    'bar'",
        "var       #text(   stateSaver=TextFieldValue.Saver   )     =    'bar'",
    ]
)
def case_expand_component_line_val_remember_with_saver(vdef):
    return (
        vdef,
        "var text by remember(stateSaver=TextFieldValue.Saver) { mutableStateOf('bar') }",
    )


@parametrize_with_cases(
    "line,expected", cases=".", prefix="case_expand_component_line_"
)
def test_expand_component_line(line, expected):

    assert expand_component_line(line, [], []) == expected


#######################################################################


@parametrize(
    line=[
        "content()",
        "          content()",
        "          content()              ",
    ]
)
def case_expand_component_line_content_cases(line):
    return (line, "content?.invoke()")


@parametrize_with_cases(
    "line,expected", cases=".", prefix="case_expand_component_line_content"
)
def test_expand_component_line_content(line, expected):

    assert expand_component_line(line, [], []) == expected


#######################################################################


@parametrize(
    line=[
        "ui {",
        "ui{",
        "          ui   {",
        "          ui{              ",
    ]
)
def case_expand_component_line_ui_render_cases(line):
    return (line, "PineRender {\n    %%PARAMSETTERSLAUNCHEDEFFECTS%%")


@parametrize_with_cases(
    "line,expected", cases=".", prefix="case_expand_component_line_ui_render"
)
def test_expand_component_line_ui_render(line, expected):

    for x,y in zip(expand_component_line(line, [], []).split(), expected.split()):
        assert x.strip() == y.strip()


#######################################################################


@parametrize(
    line=[
        "onCreate {",
        "onCreate{",
        "          onCreate   {",
        "          onCreate{              ",
    ]
)
def case_expand_component_line_onCreate_render_cases(line):
    return (line, "LaunchedEffect(true) {")


@parametrize_with_cases(
    "line,expected", cases=".", prefix="case_expand_component_line_onCreate_render"
)
def test_expand_component_line_onCreate_render(line, expected):

    assert expand_component_line(line, [], []) == expected


#######################################################################


@parametrize(
    lines=[
        ['var $foo : String = "bar"'],
        ['var $foo:String="bar"'],
        ['var       $foo       :         String         =          "bar"'],
        ['var *foo : String = "bar"'],
        ['var *foo:String="bar"'],
        ['var *foo       :        String       =          "bar"'],
        ['external var $foo : String = "bar"'],
        ['external var $foo:String="bar"'],
        ['external var       $foo        :         String       =           "bar"'],
        ['external var *foo : String = "bar"'],
        ['external var *foo:String="bar"'],
        ['external var *foo          :           String          =          "bar"'],
    ]
)
def case_get_var_declarations_external_var_def(lines):
    return lines


@parametrize_with_cases("lines", cases=".", prefix="case_get_var_declarations_")
def test_get_var_declarations_(lines):

    vars = get_var_declarations(lines)

    assert vars[0]["vname"] == "foo"
    assert vars[0]["type"] == "String"


#######################################################################


@parametrize(
    lines=[
        # This is commented out because var x
        # is pretty useless in Android Compose
        # [
        #    'var foo : String = "baz"',
        #    'var bar : String = "baz"'
        # ],
        ['var $foo : String = "baz"', 'var $bar : String = "baz"'],
        ['var *foo : String = "baz"', 'var *bar : String = "baz"'],
        ['external var foo : String = "baz"', 'external var bar : String = "baz"'],
        ['external var $foo : String = "baz"', 'external var $bar : String = "baz"'],
        ['external var *foo : String = "baz"', 'external var *bar : String = "baz"'],
    ]
)
def case_get_multiple_var_declarations_var_def(lines):
    return lines


@parametrize_with_cases(
    "lines", cases=".", prefix="case_get_multiple_var_declarations_"
)
def test_get_multiple_var_declarations_(lines):

    vars = get_var_declarations(lines)

    assert len(vars) == 2

    assert vars[0]["vname"] == "foo"
    assert vars[1]["vname"] == "bar"

    assert vars[0]["type"] == "String"
    assert vars[1]["type"] == "String"


#######################################################################


@parametrize(
    lines=[
        # var
        ['external var $foo : String = "baz"', 'external var $bar : String = "baz"'],
        ['external var $foo:String="baz"', 'external var $bar:String="baz"'],
        [
            'external var $foo  :   String   =   "baz"',
            'external var $bar  :  String  =  "baz"',
        ],
        ['external var *foo : String = "baz"', 'external var *bar : String = "baz"'],
        ['external var *foo:String="baz"', 'external var *bar:String="baz"'],
        [
            'external var *foo   :  String  =   "baz"',
            'external var *bar  :  String  =  "baz"',
        ],
        ["external var *foo : String", "external var *bar : String"],
        ["external var *foo:String", "external var *bar:String"],
        ["external var foo : String", "external var bar : String"],
        ["external var foo:String", "external var bar:String"],
        # val
        ['external val $foo : String = "baz"', 'external val $bar : String = "baz"'],
        ['external val $foo:String="baz"', 'external val $bar:String="baz"'],
        [
            'external val $foo  :   String   =   "baz"',
            'external val $bar  :  String  =  "baz"',
        ],
        ['external val *foo : String = "baz"', 'external val *bar : String = "baz"'],
        ['external val *foo:String="baz"', 'external val *bar:String="baz"'],
        [
            'external val *foo   :  String  =   "baz"',
            'external val *bar  :  String  =  "baz"',
        ],
        ["external val *foo : String", "external val *bar : String"],
        ["external val *foo:String", "external val *bar:String"],
        ["external val foo : String", "external val bar : String"],
        ["external val foo:String", "external val bar:String"],
        # mix and match
        ['external var $foo : String = "baz"', 'external var *bar : String = "baz"'],
        ['external var *foo : String = "baz"', 'external var $bar : String = "baz"'],
        ["external var $foo : String", "external var *bar: String"],
        ["external var *foo : String", "external var $bar: String"],
    ]
)
def case_get_multiple_exports_lines(lines):
    return lines


@parametrize_with_cases("lines", cases=".", prefix="case_get_multiple_exports_")
def test_get_multiple_exports(lines):

    exports = get_exports(lines)

    assert len(exports) == 2

    assert exports[0]["vname"] == "foo"
    assert exports[1]["vname"] == "bar"

    assert exports[0]["type"] == "String"
    assert exports[1]["type"] == "String"


#######################################################################


# FIXME: Should this error?
# external should have a type, but
@parametrize(
    lines=[
        # var
        ['external var $foo = "baz"', 'external var $bar = "baz"'],
        ['external var $foo="baz"', 'external var $bar="baz"'],
        [
            'external var $foo  =   "baz"',
            'external var $bar  =  "baz"',
        ],
        ['external var *foo = "baz"', 'external var *bar = "baz"'],
        ['external var *foo="baz"', 'external var *bar="baz"'],
        [
            'external var *foo   =   "baz"',
            'external var *bar  =  "baz"',
        ],
        ["external var *foo", "external var *bar"],
        ["external var *foo", "external var *bar"],
        ["external var foo ", "external var bar "],
        ["external var foo", "external var bar"],
        # val
        ['external val $foo = "baz"', 'external val $bar = "baz"'],
        ['external val $foo="baz"', 'external val $bar="baz"'],
        [
            'external val $foo  =   "baz"',
            'external val $bar  =  "baz"',
        ],
        ['external val *foo = "baz"', 'external val *bar = "baz"'],
        ['external val *foo="baz"', 'external val *bar="baz"'],
        [
            'external val *foo   =   "baz"',
            'external val *bar  =  "baz"',
        ],
        ["external val *foo", "external val *bar "],
        ["external val *foo", "external val *bar"],
        ["external val foo ", "external val bar "],
        ["external val foo", "external val bar"],
        # mix and match
        ['external var $foo = "baz"', 'external var *bar = "baz"'],
        ['external var *foo = "baz"', 'external var $bar = "baz"'],
        ["external var $foo ", "external var *bar"],
    ]
)
def case_get_multiple_exports_no_type_lines(lines):
    return lines


@parametrize_with_cases("lines", cases=".", prefix="case_get_multiple_exports_no_type_")
def test_get_multiple_exports_no_type(lines):

    exports = get_exports(lines)

    assert len(exports) == 2

    assert exports[0]["vname"] == "foo"
    assert exports[1]["vname"] == "bar"

    assert exports[0]["type"] == None
    assert exports[1]["type"] == None


#######################################################################


@parametrize(
    line_and_expected=[
        [
            "Component(bind:foo=foo)",
            """
            fun _set_foo(value: String) {
                foo = value
                _set_foo_incoming_?.invoke(foo)
            }
            Component(_set_foo_incoming_=::_set_foo, foo=foo)""",
        ],
        [
            "Component(bind:foo=foo, bar=bar)",
            """
            fun _set_foo(value: String) {
                foo = value
                _set_foo_incoming_?.invoke(foo)
            }
            Component(_set_foo_incoming_=::_set_foo, foo=foo, bar=bar)""",
        ],
        [
            "Component(bind:foo=first, bar=bar)",
            """
            fun _set_foo(value: String) {
                foo = value
                _set_foo_incoming_?.invoke(foo)
            }
            Component(_set_foo_incoming_=::_set_foo, foo=first, bar=bar)""",
        ],
        [
            "Component(baz=baz, bind:foo=first, bar=bar)",
            """
            fun _set_foo(value: String) {
                foo = value
                _set_foo_incoming_?.invoke(foo)
            }
            Component(baz=baz, _set_foo_incoming_=::_set_foo, foo=first, bar=bar)""",
        ],
    ]
)
def case_expand_component_lines_single_binding_component(line_and_expected):
    return line_and_expected


@parametrize_with_cases(
    "line_and_expected", cases=".", prefix="case_expand_component_lines_single_binding_"
)
def test_expand_component_lines_single_binding(line_and_expected):

    line, expected = line_and_expected

    vars = [{"vname": "foo", "type": "String"}]
    exports = [{"vname": "foo", "type": "String"}]

    assert expand_component_line(line, vars, exports) == expected


#######################################################################


@parametrize(
    line_and_expected=[
        [
            "Component(bind:foo=foo, bind:bar=bar)",
            """
            fun _set_foo(value: String) {
                foo = value
                _set_foo_incoming_?.invoke(foo)
            } 
            
            fun _set_bar(value: String) {
                bar = value
                _set_bar_incoming_?.invoke(bar)
            }
            Component(_set_foo_incoming_=::_set_foo, foo=foo, _set_bar_incoming_=::_set_bar, bar=bar)""",
        ],
    ]
)
def case_expand_component_lines_multiple_binding_component(line_and_expected):
    return line_and_expected


@parametrize_with_cases(
    "line_and_expected",
    cases=".",
    prefix="case_expand_component_lines_multiple_binding_",
)
def test_expand_component_lines_multiple_binding(line_and_expected):

    line, expected = line_and_expected

    vars = [{"vname": "foo", "type": "String"}, {"vname": "bar", "type": "String"}]
    exports = [{"vname": "foo", "type": "String"}, {"vname": "bar", "type": "String"}]

    assert _clean_lines(expand_component_line(line, vars, exports)) == _clean_lines(
        expected
    )


#######################################################################


@parametrize(
    lines=[
        """---
import foo.bar
import baz.foo.a.b.c.d.e

data class Foo(
a: String,
b: String,
c: String
)
---
a
b
c
d
e
f
g
h
i 
j""".split(
            "\n"
        )
    ]
)
def case_get_frontmatter_(lines):
    return lines


@parametrize_with_cases(
    "lines",
    cases=".",
    prefix="case_get_frontmatter_",
)
def test_get_frontmatter(lines):

    frontmatter = get_frontmatter(lines)
    assert len(frontmatter["imports"]) == 2
    assert frontmatter["imports"][0] == "import foo.bar"
    assert frontmatter["imports"][1] == "import baz.foo.a.b.c.d.e"

    assert len(frontmatter["frontmatter"]) == 6

    assert len(frontmatter["rest"]) == 10


#######################################################################
