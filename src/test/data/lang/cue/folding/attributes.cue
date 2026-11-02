<fold text='@if' expand='true'>@if(this)</fold>
import "strconv"
_: strconv
a: 1

b: <fold text='{...}' expand='true'>{
    <fold text='@package' expand='true'>@package(value)</fold>
    <fold text='@number' expand='true'>@number(value)</fold>
    <fold text='@null' expand='true'>@null(value)</fold>
    <fold text='@true' expand='true'>@true(value)</fold>
    <fold text='@my_name' expand='true'>@my_name(value)</fold>
}</fold>
