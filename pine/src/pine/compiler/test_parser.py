from pine.compiler.parser import expand_component_line, get_var_declarations
from pytest_cases import parametrize_with_cases, parametrize


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
        "var $foo = 'bar'",
        "var $foo='bar'",
        "var $foo        =          'bar'",
    ]
)
def case_expand_component_line_var_remember(vdef):
    return (
        vdef,
        "var foo by remember { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "var $foo    :    String       =          'bar'",
        "var $foo:String='bar'",
        "var $foo : String = 'bar'",
    ]
)
def case_expand_component_line_var_remember_with_type(vdef):
    return (
        vdef,
        "var foo : String by remember { mutableStateOf('bar') }",
    )







@parametrize(
    vdef=[
        "val $foo = 'bar'",
        "val $foo='bar'",
        "val $foo        =          'bar'",
    ]
)
def case_expand_component_line_val_remember(vdef):
    return (
        vdef,
        "val foo by remember { mutableStateOf('bar') }",
    )


@parametrize(
    vdef=[
        "val $foo    :    String       =          'bar'",
        "val $foo:String='bar'",
        "val $foo : String = 'bar'",
    ]
)
def case_expand_component_line_val_remember_with_type(vdef):
    return (
        vdef,
        "val foo : String by remember { mutableStateOf('bar') }",
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
        "var $text(stateSaver=TextFieldValue.Saver) = 'bar'",
        "var       $text(stateSaver=TextFieldValue.Saver)     =    'bar'",
        "var       $text(   stateSaver=TextFieldValue.Saver   )     =    'bar'",
    ]
)
def case_expand_component_line_val_remember_with_saver(vdef):
    return (
        vdef,
        "var text by remember(stateSaver=TextFieldValue.Saver) { mutableStateOf('bar') }",
    )











@parametrize_with_cases("line,expected", cases=".", prefix="case_expand_component_line_")
def test_expand_component_line(line, expected):

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

    vars =  get_var_declarations(lines)

    assert vars[0]['vname'] == 'foo'
    assert vars[0]['type'] == 'String'


#######################################################################


@parametrize(
    lines=[
        [
            'var $foo : String = "baz"',
            'var $bar : String = "baz"'
         ],
        [
            'external var $foo : String = "baz"',
            'external var $bar : String = "baz"'
         ],
        [
            'external var *foo : String = "baz"',
            'external var *bar : String = "baz"'
         ],
    ]
)
def case_get_multiple_var_declarations_var_def(lines):
    return lines

@parametrize_with_cases("lines", cases=".", prefix="case_get_multiple_var_declarations_")
def test_get_multiple_var_declarations_(lines):

    vars =  get_var_declarations(lines)

    assert len(vars) == 2

    assert vars[0]['vname'] == 'foo'
    assert vars[1]['vname'] == 'bar'

    assert vars[0]['type'] == 'String'
    assert vars[1]['type'] == 'String'


#######################################################################
