 ## Mi CV
 
 ***

### App de muestra 

Esta App ha sido diseñada y desarrollada con el objetivo de practicar los conocimientos en **Android-Studio**. Inicialmente se programa usando el lenguaje Java aunque se prevé exportar el código a __Kotlin__. 

Si le resulta más cómodo puede ver el vídeo explicativo [youtube]

***
#### Contruido con:

- Android Studio
    - Java
    - Realm (DDBB)
    - Glide (carga de imagen desde Internet)
    - WebView (mostrar web embebidas)

***
#### __Esquema__:

![Screenshot](/imagenMD/conjunto.png)


 ***
#### Inicio

Se inicia con una pantalla explicativa __MainActivity__, la cual muestra un menú lateral, que permite el acceso a las restantes pantallas.  Para las cuales se han usado `FragmentActivity`. El MainActivity posee un `FloatingActionButton (FAB)` que se encuentra oculto y se mostrará según las necesidades de los Fragment.

<p align="center"> <img src="/imagenMD/imagen1.png" width="200"/> <img src="/imagenMD/imagen2.png" width="200"/> </p> 



 ***
#### Datos

La Aplicación consta de tres bases de datos usan objetos `Realm`. Cada una almacena  referencias a los aspectos de mi Curriculum Vitae tales como: Titulaciones, Cursos y Empleos. Toda esa informacion se muestra en su propio `Recycler View` con formato de tarjeta `CardView`. En cada tarjeta se puede leer la información de cada registro. Incluso se pude añadir nuevos registros, modificarlos o borrarlos. Para acceder a las modificaciones se ha usado un `alerdDialog`. 


También se muestra una imagen, que puede ser el logo de la empresa, centro de estúdio o trabajo. Para cargar dicha imagen se realiza  directamente desde Internet, usando la librería `Glide`.

<p align="center"> <img src="/imagenMD/imagen13.png" width="190"/> <img src="/imagenMD/imagen3.png" width="190"/> <img src="/imagenMD/imagen9.png" width="190"/> </p>

  


 ***
#### Mapas

Al pulsar en el centro de la tarjeta, se mostrará los detalles de cada registro y un boton flotante `FAB` que le da acceso a la Geolocalización en el `**Maps**` de la empresa o centro donde se ha estudiado o trabajado.
<p align="center"> <img src="/imagenMD/imagen7.png" width="200"/> <img src="/imagenMD/imagen8.png" width="200"/>  </p>


 ***
#### Link embebidos
El menú lateral también le dara acceso "embebido" a cada una  de los enlaces a mi presencia digital en la web. Es decir puede leer el LinkedIn, mi blog o acceder a mi repositorio de GitHub, sin usar navegador o salir de la aplicación. Para ello se usa la Clase `WebView`.
<p align="center"> <img src="/imagenMD/imagen10.png" width="190"/> <img src="/imagenMD/imagen11.png" width="190"/> <img src="/imagenMD/imagen12.png" width="190"/ </p>



 ***
#### Agradecimiento

>**Empieza a comportarte como quien te gustaría ser y con el tiempo... lo serás**



Espero que le guste mi trabajo. 

Saludos. 

[youtube]:https://www.youtube.com/watch?v=RdfHRFbRMWc
