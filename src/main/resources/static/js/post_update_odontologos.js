window.addEventListener('load', function () {

    const urlParams = new URLSearchParams(window.location.search);
    const odontologoId = urlParams.get('id');
    const formulario = document.querySelector('#form_odontologo');
    const botonEnvio = document.querySelector('#btn-form-odontologo');
    const tituloFormulario = document.querySelector('#titulo-formulario');

    let modo = 'creacion'

    // Si tiene el parametro id es por que es una actualizacio y se busca el odontologo con ese id
    if(odontologoId) {

        const url = '/odontologos/buscar/' + odontologoId;
        const settings = {
            method: 'GET'
        };

        fetch(url,settings)
        .then(response => {
            if (!response.ok) {

               throw new Error('No se encontró el registro');
            }
            return response.json();
        })
        .then(data => {

            if(data) {
              tituloFormulario.innerHTML = 'Actualizar Odontologo';
              modo = 'edicion';
              document.querySelector('#link-alta').style.display = "block";
              botonEnvio.innerText = 'Actualizar'

              document.querySelector('#matricula').value = data.matricula;
              document.querySelector('#nombre').value = data.nombre;
              document.querySelector('#apellido').value = data.apellido;
            }else {
                throw new Error('No se encontró el registro');
            }

        })
        .catch(error => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<button type="button" class="btn btn-danger" onclick="quitarAlerta()">&times;</button>' +
                        '<strong> No se encontro ese registro</strong> </div>'

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        })
    }

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva odontologo



    //Ante un click del boton de envio se ejecutará la siguiente funcion
    botonEnvio.addEventListener('click', function (event) {

       //creamos un JSON que tendrá los datos de la nueva odontologo
        const formData = {
            id: odontologoId !== null ? Number(odontologoId) : null,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,

        };

        if (!formData.matricula || !formData.nombre || !formData.apellido) {
                alert('Por favor, complete todos los campos antes de enviar.');
            return;
        }

        //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
        //la película que enviaremos en formato JSON
        let url = '/odontologos';

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

         if(modo === 'edicion') {
            settings.method = 'PUT'
        }

        fetch(url, settings)
            .then(() => {


                 //Si no hay ningun error se muestra un mensaje diciendo que el odontologo
                 //se agrego bien

                 let mensajeModo = 'Odontologo agregado'

                  if(modo === 'edicion')  {
                     mensajeModo = 'Odontologo actualizado'
                  }

                 let successAlert = `
                 <div class="alert alert-success alert-dismissible">
                     <button type="button" class="btn btn-success" onclick="quitarAlerta()" >&times;</button>
                     <strong>${mensajeModo}</strong>
                 </div>`;

                 const divRespuesta = document.querySelector('#response');
                 divRespuesta.innerHTML = successAlert;
                 divRespuesta.style.display = "block";


                 if(modo === 'creacion')  {
                    resetForm();
                 }

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que la pelicula
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button  class="btn btn-danger" type="button" onclick="quitarAlerta()">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";
            })
    });

    function resetForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }


    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_all_odontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});

function quitarAlerta() {
        document.querySelector('#response').style.display = "none";
}