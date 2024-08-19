from antlr4 import *
from antlr4.tree.Trees import Trees

from kotlin.KotlinLexer import KotlinLexer
from kotlin.KotlinParser import KotlinParser


FILE = "src/routes/+screen.pine"
FILE = "dist/cupcake/app/src/main/java/com/example/cupcake/ui/RootScreen.kt"
FILE = "src/components/simple.pine"

fp = open(FILE, "r")
code = fp.read()

print(code)

# code = """
# fun hello() {
# println("foo")
# }
# """


def get_parser(code):

    lexer = KotlinLexer(InputStream(code))
    stream = CommonTokenStream(lexer)
    parser = KotlinParser(stream)
    return parser


parser = get_parser(code)
tree = parser.kotlinFile()
print(tree.toStringTree(recog=parser))

print("----")
parser = get_parser(code)
tree = parser.kotlinFile()
for i, t in enumerate(list(tree.getChildren())):
    print(i, t.getText())

# children =  list(tree.getChildren())
# for child in children:
# print(child)
# pass

print("----")
# for t in lexer.getAllTokens():
# print(t.text, t.type)
