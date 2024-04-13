# Bidify
## Escuela Colombiana de Ingeniería Julio Garavito
### Innovación y emprendimiento TI
### Integrantes:
* Jaider Arley Gonzales Arias
* Juan Pablo Daza Pinzón
* Juan Sebastian Rodriguez Peña
* Miguel Angel Barrera Diaz

## ¿Que es Bidify?

Bidify es una plataforma innovadora la cual tiene como objetivo  fusionar las redes sociales con las subastas en tiempo real, brindando a los usuarios una experiencia unica de compra y  venta. Llevando a los usuarios en emocionantes subastas interactivas, donde los usuarios tienen la oportunidad de descubrir productos exclusivos mientras se participa en las subastas, comparte tus descubrimientos con otro usuarios, crea nuevas conexiones a traves de las subastas, asi que adquiere productos de manera unica y participativa con Bidify.

## El uso de IA generativa

La Inteligencia Artificial hoy en día es una gran tecnología que nos permite apoyarnos en la creacion de ideas de negocio, codigo e interfaces de usuario.</br>

En Bidify decidimos usar una IA generativa que nos ayudara a darnos una idea de 
como podria ser nuestra interfaz de usuario. Consultamos algunas IA que generaran 
interfaces de suaurio y encontramos [Galileo AI](https://www.usegalileo.ai/explore) le pedimos que nos diseñara una interfaz donde el usuario pueda ver subastas
y una pestaña de usuario para que el usuario pueda ver sus subastas.</br>

![](/img/InterfazSubastas.PNG)</br>

Tambien le pedimos que nos hiciera una interfaz del perfil del usuario en donde
se viera el nombre y las subastas que tiene activas.

![](/img/InterfazUsuario.PNG)</br>

Con Galileo AI logramos imaginarnos mas un poco de como se veria nuestra aplicación.</br>

Para el codigo estuvimos usando [ChatGPT](https://chat.openai.com) y debido a que 
la Universidad tiene un convenio con GitHub tambien pudimos hacer uso de 
[GitHub Copilot](https://github.com/features/copilot). Estas dos IA son muy 
utiles a la hora de explicar la solucion a diferentes problemas en el codigo y
a la sugerencia de lineas de codigo basadas en otros repositorios abiertos.

Link del Video: https://youtu.be/qjm67KJfJLg


## Instalacion requisitos AWS ☁️

Ejecute los siguientes comandos para instalar git, maven y java en su máquina EC2.

Instalación git:

    sudo yum install -y git

Instalación java:

    sudo yum install -y java-17-amazon-corretto-devel

Instalación maven:

    sudo wget https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
    sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
    sudo yum install -y apache-maven

Instalación Docker:

    sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose version


## Instalación 🔧

Realice los siguientes pasos para clonar el proyecto en su máquina local.

    git clone https://github.com/MiguelBarreraD/Bidify_Project.git
    cd Bidify_Project/

## Ejecutando la aplicación ⚙️

> [!IMPORTANT]
> Debemos tener configurado el puerto 8080 en el grupo de seguridad de nuestra máquina virtual.

Para compilar y ejecutar el proyecto se debe ejecutar los siguientes comando:

    mvn clean install
    docker-compose up -d


## Test

Usamos el siguiente comando para ejecutar las pruebas unitarias:

    mvn test


![](/img/Test.PNG)</br>

### Controller

#### Productos
Las pruebas unitarias para la clase ProductoController en una aplicación Java. Las pruebas cubren las siguientes funcionalidades:


```java
    // Verifica que el método listarProductos() devuelva una lista de productos no nula y que tenga un tamaño igual a 1.
    @Test
    public void testListarProductos() {
        List<Producto> productos = productoController.listarProductos();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    // Prueba que el método obtenerProductoPorId() devuelva un producto no nulo con el ID especificado (en este caso, 1L).
    @Test
    public void testObtenerProductoPorId() {
        Producto producto = productoController.obtenerProductoPorId(1L);
        assertNotNull(producto);
        assertEquals(1L, producto.getId());
    }

    // Comprueba que el método obtenerProductoPorNombre() devuelva un producto no nulo con el nombre especificado ("Producto 1").
    @Test
    public void testObtenerProductoPorNombre() {
        Producto producto = productoController.obtenerProductoPorNombre("Producto 1");
        assertNotNull(producto);
        assertEquals("Producto 1", producto.getNombre());
    }

    //  Verifica que el método crearProducto() cree un nuevo producto con los valores proporcionados y que el producto devuelto sea no nulo y tenga los mismos valores que se establecieron.
    @Test
    public void testCrearProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto 2");
        producto.setPrecio(200);
        producto.setCantidad(5);

        Producto productoCreado = productoController.crearProducto(producto);
        assertNotNull(productoCreado);
        assertEquals("Producto 2", productoCreado.getNombre());
        assertEquals(200, productoCreado.getPrecio());
        assertEquals(5, productoCreado.getCantidad());
    }

    //  Prueba que el método actualizarProducto() actualice correctamente un producto existente con los valores proporcionados y que el producto actualizado sea no nulo y tenga los mismos valores que se establecieron.
    @Test
    public void testActualizarProducto() {
        Producto producto = productoController.obtenerProductoPorId(1L);
        producto.setNombre("Producto 1 Actualizado");
        producto.setPrecio(150);
        producto.setCantidad(10);

        Producto productoActualizado = productoController.actualizarProducto(1L, producto);
        assertNotNull(productoActualizado);
        assertEquals("Producto 1 Actualizado", productoActualizado.getNombre());
        assertEquals(150, productoActualizado.getPrecio());
        assertEquals(10, productoActualizado.getCantidad());
    }

    // Verifica que el método eliminarProducto() elimine correctamente un producto y que al intentar obtener el producto eliminado, devuelva nulo.
    @Test
    public void testEliminarProducto() {
        productoController.eliminarProducto(1L);
        Producto producto = productoController.obtenerProductoPorId(1L);
        assertNull(producto);
    }
```

#### Subastas

```java
    //  Verifica que el método createSubasta() del controlador funcione correctamente al agregar una subasta y devolver una respuesta HTTP OK, además de verificar que el método addSubasta() del servicio se llame una vez con el argumento correcto.
    @Test
    void testCreateSubasta() {
    }

    // Prueba que el método getAllSubastas() del controlador funcione correctamente al obtener todas las subastas y devolver una respuesta HTTP OK, además de verificar que el método findAllSubastas() del servicio se llame una vez.
    @Test
    void testGetAllSubastas() {
    }

    // Verifica que el método getSubastaById() del controlador funcione correctamente al obtener una subasta por su ID y devolver una respuesta HTTP OK, además de verificar que el método getSubastaById() del servicio se llame una vez con el ID correcto.
    @Test
    void testGetSubastaById() {
    }

    // Prueba que el método getSubastaById() del controlador maneje correctamente el caso en que no se encuentre la subasta y devuelva una respuesta HTTP NOT_FOUND.
    @Test
    void testGetSubastaByIdNotFound() {
    }

    // Verifica que el método getMessageList() del controlador funcione correctamente al obtener la lista de mensajes de una subasta y devolver una respuesta HTTP OK, además de verificar que el método getMessageList() del servicio se llame una vez con el ID de subasta correcto.
    @Test
    void testGetMessageList() {
    }

    // Prueba que el método getMessageList() del controlador maneje correctamente el caso en que no se encuentre la lista de mensajes de la subasta y devuelva una respuesta HTTP NOT_FOUND.
    @Test
    void testGetMessageListNotFound() {
    }
    // Verifica que el método recibirPuja() del controlador funcione correctamente al recibir una puja en una subasta activa y devolver una respuesta HTTP OK, además de verificar que el método getSubastaById() del servicio se llame una vez con el ID de subasta correcto y que el método isEstado() de la subasta se llame una vez.
    @Test
    void testRecibirPuja() {
    }

    // Prueba que el método recibirPuja() del controlador maneje correctamente el caso en que la subasta no se encuentre y devuelva una respuesta HTTP BAD_REQUEST, además de verificar que el método getSubastaById() del servicio se llame una vez con el ID de subasta correcto y que el método recibirPuja() del servicio no se llame.
    @Test
    void testRecibirPujaBadRequest() {
    }

```

#### Usuario

```java
    //  Verifica que el método listUsuarios() del controlador funcione correctamente al devolver una lista de usuarios y una respuesta HTTP OK. Utiliza MockMvc para simular una solicitud HTTP GET a la URL /usuario/lista y espera que la respuesta tenga un código de estado OK y un cuerpo que sea un array JSON.
    @Test
    public void listUsuariosTest() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "userName", "nombre", "email", "password", Arrays.asList(RolEnum.ROLE_USER)));
        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/usuario/lista"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    //Prueba que el método registrarUsuario() del controlador funcione correctamente al registrar un nuevo usuario y devolver una respuesta HTTP OK. Utiliza MockMvc para simular una solicitud HTTP POST a la URL /usuario/registrar con un cuerpo JSON que representa un nuevo usuario y espera que la respuesta tenga un código de estado OK.
    @Test
    public void registrarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(1, "userName", "nombre", "email", "password", Arrays.asList(RolEnum.ROLE_USER));
        usuario.setUserName("test");
        usuario.setPassword("password");

        mockMvc.perform(post("/usuario/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

```

### Service 

#### Producto



1. testList: Verifica que el método list() del servicio funcione correctamente al obtener una lista de productos y que la lista no sea nula, tenga el tamaño esperado y contenga los productos correctos.

2. testGetOne: Prueba que el método getOne() del servicio funcione correctamente al obtener un producto por su ID y que el producto devuelto no sea nulo y sea el esperado.

3. testGetByNombre: Verifica que el método getByNombre() del servicio funcione correctamente al obtener un producto por su nombre y que el producto devuelto no sea nulo y sea el esperado.

4. testSave: Prueba que el método save() del servicio funcione correctamente al guardar un nuevo producto y que el producto devuelto no sea nulo y sea el esperado.

5. testDelete: Verifica que el método delete() del servicio funcione correctamente al eliminar un producto por su ID.

6. testExistsById: Prueba que el método existsById() del servicio funcione correctamente al verificar si un producto existe por su ID.

7. testExistsByNombre: Verifica que el método existsByNombre() del servicio funcione correctamente al verificar si un producto existe por su nombre.

8. testUpdate: Prueba que el método update() del servicio funcione correctamente al actualizar un producto y que el producto actualizado tenga los valores esperados.

9. testAutoIncrement: Verifica que el método autoIncrement() del servicio funcione correctamente al obtener el siguiente ID disponible para un nuevo producto.

#### Subasta

1. testAddSubasta: Verifica que el método addSubasta() del servicio funcione correctamente al agregar una nueva subasta y que la subasta devuelta no sea nula y sea la esperada.

2. testFindAllSubastas: Prueba que el método findAllSubastas() del servicio funcione correctamente al obtener todas las subastas y que la lista de subastas no sea nula, tenga el tamaño esperado y contenga las subastas correctas.

3. testGetSubastaById: Verifica que el método getSubastaById() del servicio funcione correctamente al obtener una subasta por su ID y que la subasta devuelta no sea nula y sea la esperada.

4. testGetSubastaByProducto: Prueba que el método getSubastaByProducto() del servicio funcione correctamente al obtener una subasta por su producto asociado y que la subasta devuelta no sea nula y sea la esperada.

#### Usuario

1. testDeleteUserName: Verifica que el método deleteUserName() del servicio funcione correctamente al eliminar un usuario por su nombre de usuario.

2. testUpdateUser: Prueba que el método updateUser() del servicio funcione correctamente al actualizar un usuario y que se guarde en el repositorio.

3. testLogin: Verifica que el método login() del servicio funcione correctamente al autenticar un usuario y devolver true si las credenciales son válidas.

4. testFindAll: Prueba que el método findAll() del servicio funcione correctamente al obtener todos los usuarios y que la lista de usuarios no sea nula, tenga el tamaño esperado y contenga los usuarios correctos.

5. testRegistrarUsuario: Verifica que el método registrarUsuario() del servicio funcione correctamente al registrar un usuario y que se guarde en el repositorio.

6. testCrearUsuario: Prueba que el método crearUsuario() del servicio funcione correctamente al crear un nuevo usuario y que el usuario creado tenga los atributos esperados.

7. testExistsById: Verifica que el método existsById() del servicio funcione correctamente al verificar si un usuario existe por su ID.

8. testExistsByUserName: Prueba que el método existsByUserName() del servicio funcione correctamente al verificar si un usuario existe por su nombre de usuario.

### Model

#### Producto

1. testConstructor: Verifica que el constructor de Producto inicialice correctamente los atributos del producto con los valores proporcionados y que los métodos getId(), getNombre(), getPrecio() y getImg() devuelvan los valores esperados.

2. estSetters: Prueba que los métodos setId(), setNombre(), setPrecio() y setImg() de la clase Producto funcionen correctamente al establecer los valores de los atributos y que los métodos getId(), getNombre(), getPrecio() y getImg() devuelvan los nuevos valores establecidos.


#### Subasta

1. testConstructor: Verifica que el constructor de Subasta inicialice correctamente los atributos de la subasta con los valores proporcionados y que los métodos getSubastador(), getProducto(), getPrecioInicial(), isEstado(), getCantidadDeOfertantes(), getMessageList() y getPrecioFinal() devuelvan los valores esperados. También se verifica que la lista de mensajes esté inicializada y vacía.

2. testAddMessage: Prueba que el método addMessage() de la clase Subasta funcione correctamente al agregar un mensaje a la lista de mensajes de la subasta y que la lista de mensajes no esté vacía y contenga el mensaje agregado.

#### Usuario

1. testConstructor: Verifica que el constructor de Usuario inicialice correctamente los atributos del usuario con los valores proporcionados y que los métodos getId(), getUserName(), getNombre(), getEmail(), getPassword() y getRoles() devuelvan los valores esperados. También se verifica que la lista de productos esté inicializada y vacía.

2. testAddProducto: Prueba que el método addProducto() de la clase Usuario funcione correctamente al agregar un producto a la lista de productos del usuario y que la lista de productos no esté vacía y contenga el producto agregado.

3. testRemoveProducto: Verifica que el método removeProducto() de la clase Usuario funcione correctamente al eliminar un producto de la lista de productos del usuario y que la lista de productos esté vacía después de la eliminación.


