from antlr4 import *

from kotlin.KotlinLexer import KotlinLexer
from kotlin.KotlinParser import KotlinParser


FILE = "src/routes/+screen.pine"

fp = open(FILE, "r")
code = fp.read()

code = '''
fun hello() {
}
'''

lexer = KotlinLexer(code)
stream = CommonTokenStream(lexer)
parser = KotlinParser(stream)
tree = parser.r()
print(tree.toStringTree(recog=parser))
import pdb; pdb.set_trace()
