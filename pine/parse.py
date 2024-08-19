from antlr4 import *
from antlr4.tree.Trees import Trees

from kotlin.KotlinLexer import KotlinLexer
from kotlin.KotlinParser import KotlinParser


FILE = "src/routes/+screen.pine"
FILE = "dist/cupcake/app/src/main/java/com/example/cupcake/ui/RootScreen.kt"
FILE = "src/components/simple.pine"

fp = open(FILE, "r")
code = fp.read()

# code = """
# fun hello() {
# println("foo")
# }
# """

lexer = KotlinLexer(InputStream(code))
stream = CommonTokenStream(lexer)
parser = KotlinParser(stream)
tree = parser.kotlinFile()
#print(tree.toStringTree(recog=parser))

#children =  list(tree.getChildren())
#for child in children:
    #print(child)
    #pass

print('----')
for t in lexer.getAllTokens():
    print(t.text, t.type)

    