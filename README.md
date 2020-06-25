 ##Mi CV
 
 
***
###App de muestra 

Esta App ha sido diseñada y desarrollada con el obejtivo de practicar los conocimientos en **Android-Studio**. Inicialmente se programa usando el lenguaje Java aunque se prevee exportar el código a __Kotlin__. 

Si le resulta más cómodo puede ver el vídeo explicativo [youtube]
***
 

####__Esquema__:

![TExto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/conjunto.png "Esquema de la App")

 ***
####Inicio

Se inicia con una pantalla explicativa MainActivity, la cual muestra un menú lateral, que permite el acceso a las pantallas.  Para las cuales se han usado FragmentActivity. El MainActivity posee un FloatingActionButton que se encuentra en hide() y se mostrará según las necesidades den los Fragment. 

![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen1.png "MainActiviy y Menú Lateral")  ![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen2.png "MainActiviy y Menú Lateral")  



 ***
####Datos

La Aplicación consta de tres bases de datos usan objetos Realm. Cada una almacena  referencias a los aspectos de mi Curriculum Vitae tales como: Titulaciones, Cursos y Empleos. Toda esa informacion se muestra en su propio 'Recycler View' con formato el de tarjeta 'CardView'. En cada tarjeta se puede leer la información de cada registro. Incluso se pude añadir nuevos registros, modivicarlos o borrarlos. Para acceder a las modificaciones se ha usado un alerdDialog. 


También se muestra una imagen, que puede ser el logo de la empresa, centro de estúdio o trabajo. Para cargar dicha imagen se realiza  directamente desde Internet, usando la librería Glide.

![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen13.png "Trabajos Fragment") ![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen3.png "Titulos Fragment") ![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen9.png "Cursos Fragment")  


 ***
####Mapas

Al hacer click en el centro de la tarjeta, se mostrará los detalles de cada registro y un boton flotante FAB que le da acceso a la Geolocalización en el Maps de la empresa o centro donde se ha estudiado o trabajado.


![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen7.png "Detalles Fragment")  ![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen8.png "Maps Activity")


 ***
####Link embebidos
El menú lateral también le dara acceso "embebido" a cada una  de los enlaces a mi presencia digital en la web. Es decir puede leer el LinkedIn, mi blog o acceder a mi repositorio de GitHub, sin salir de la aplicación o usar " "navegador. Para ello se usa la Clase WebView.


![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen10.png "Trabajos Fragment")
 ![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen11.png "Trabajos Fragment") ![Texto alternativo](/home/jorge/AndroidStudioProjects/MyCV/imagenMD/imagen12.png "Trabajos Fragment")


 ***
####Agradecimiento
>Empieza a comportarte como quien te gustaría ser y con el tiempo... lo serás<



Espero que le guste mi trabajo. 

Saludos. 

[youtube]:https://www.youtube.com/watch?v=RdfHRFbRMWc