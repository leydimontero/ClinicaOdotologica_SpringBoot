window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará el  nuevo paciente
    const urlParams = new URLSearchParams(window.location.search);
    const pacienteId = urlParams.get('id');
    const formulario = document.querySelector('#form_paciente');
    const botonEnvio = document.querySelector('#btn-form-paciente');
    const tituloFormulario = document.querySelector('#titulo-formulario');

    let modo = 'creacion'

    // Si tiene el parametro id es por que es una actualizacion y se busca el paciente con ese id
    if(pacienteId) {

        const url = '/pacientes/buscar/' + pacienteId;
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
              tituloFormulario.innerHTML = 'Actualizar Paciente';
              modo = 'edicion';
              document.querySelector('#link-alta').style.display = "block";
              botonEnvio.innerText = 'Actualizar'

              document.querySelector('#nombre').value = data.nombre;
              document.querySelector('#apellido').value = data.apellido;
              document.querySelector('#cedula').value = data.cedula;
              document.querySelector('#fechaIngreso').value = data.fechaIngreso;
              document.querySelector('#calle').value = data.domicilio.calle;
              document.querySelector('#numero').value = data.domicilio.numero;
              document.querySelector('#localidad').value = data.domicilio.localidad;
              document.querySelector('#provincia').value = data.domicilio.provincia;
              document.querySelector('#email').value = data.email;

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


    //Ante un submit del formulario se ejecutará la siguiente funcion
    botonEnvio.addEventListener('click', function (event) {

       //creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            id: pacienteId !== null ? Number(pacienteId) : null,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: parseInt(document.querySelector('#numero').value, 10),
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },
            email: document.querySelector('#email').value,

        };

        if (!formData.cedula || !formData.nombre || !formData.apellido ||
            !formData.fechaIngreso || !formData.domicilio.calle ||
            !formData.domicilio.numero || !formData.email) {
                        alert('Por favor, complete todos los campos antes de enviar.');
             return;
        }

        //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/pacientes';
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
            //Si no hay ningun error se muestra un mensaje diciendo que la pelicula
            //se agrego bien
            let mensajeModo = 'Paciente agregado'

              if(modo === 'edicion')  {
                 mensajeModo = 'Paciente actualizado'
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
                resetUploadForm();
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
        });

    })
    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#cedula').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = null;
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
        document.querySelector('#email').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/get_all_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});

function quitarAlerta() {
        document.querySelector('#response').style.display = "none";
}