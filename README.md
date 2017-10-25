# batik-pro
####Abstract
This is a machine engine on java to convert svg file to png/jpg/pdf/tiff. 
<br/>It's based pn apache batik but more robust than batik.

###Fix Bug
1. Can't read **filter** tag, resolve image conflict against new java version(above 1.6).
2. Merge png of base64 error, convert BufferedImage from TYPE\_BYTE\_INDEXED to TYPE\_INT\_ARGB