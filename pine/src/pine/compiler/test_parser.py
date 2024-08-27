from pine.compiler.parser import expand_component_line
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
def case_external_var(vdef):
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
def case_external_val(vdef):
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
def case_var_remember(vdef):
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
def case_var_remember_with_type(vdef):
    return (
        vdef,
        "var foo : String by remember { mutableStateOf('bar') }",
    )


@parametrize_with_cases("line,expected", cases=".")
def test_expand_component_line(line, expected):

    assert expand_component_line(line, [], []) == expected
