<fold text='@if'>@if(this)</fold>
import "strconv"
_: strconv
a: 1

b: <fold text='{...}'>{
    <fold text='@package'>@package(value)</fold>
    <fold text='@number'>@number(value)</fold>
    <fold text='@null'>@null(value)</fold>
    <fold text='@true'>@true(value)</fold>
    <fold text='@my_name'>@my_name(value)</fold>
}</fold>