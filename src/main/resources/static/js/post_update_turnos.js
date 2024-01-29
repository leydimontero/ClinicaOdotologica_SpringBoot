window.addEventListener('load', function () {

    const urlParams = new URLSearchParams(window.location.search);
    const turnoId = urlParams.get('id');
    const botonEnvio = document.querySelector('#btn-form-turno')
    const tituloFormulario = document.querySelector('#titulo-formulario');
    const selectOdontologos = document.querySelector('#select_odontologos');
    const selectPacientes = document.querySelector('#select_pacientes');
    let modo = 'creacion';

    // Función para traer todos los odontologos y llenar el seleccionable
    (function(){

        const url = '/odontologos';
        const settings = {
            method: 'GET'
        };

        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
        //recorremos la colección de odontologos del JSON

         for(odontologo of data){

            const option = document.createElement('option');
            option.value = odontologo.id;
            option.textContent = odontologo.nombre;
            selectOdontologos.appendChild(option);
         }
         selectOdontologos.value = null;
        });
    })();

    // Función para traer todos los pacientes llenar el seleccionable
    (function(){

        const url = '/pacientes';
        const settings = {
            method: 'GET'
        };

        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
        //recorremos la colección de pacientes del JSON

         for(paciente of data){

            const option = document.createElement('option');
            option.value = paciente.id;
            option.textContent = paciente.nombre;
            selectPacientes.appendChild(option);
         }

         selectPacientes.value = null;
        });
    })();


    if(turnoId) {

        const url = '/turnos/buscar/' + turnoId;
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
            console.log(data)
            if(data) {
              tituloFormulario.innerHTML = 'Actualizar Turno';
              modo = 'edicion';
              document.querySelector('#link-alta').style.display = "block";
              botonEnvio.innerText = 'Actualizar'

              document.querySelector('#fecha').value = data.fecha;
              document.querySelector('#select_odontologos').value = data.odontologoId;
              document.querySelector('#select_pacientes').value = data.pacienteId;
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


    botonEnvio.addEventListener('click', function (event) {

           //creamos un JSON que tendrá los datos de la nueva odontologo
            const formData = {
                id: turnoId !== null ? Number(turnoId) : null,
                fecha: document.querySelector('#fecha').value,
                paciente: {
                    id: Number(document.querySelector('#select_pacientes').value)
                },
                odontologo: {
                    id: Number(document.querySelector('#select_odontologos').value)
                }

            };

            if (!formData.fecha || !formData.paciente?.id || !formData.odontologo?.id) {
                    alert('Por favor, complete todos los campos antes de enviar.');
                return;
            }


            //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
            //la película que enviaremos en formato JSON
            let url = '/turnos';

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

                     let mensajeModo = 'Turno agregado'

                      if(modo === 'edicion')  {
                         mensajeModo = 'Turno actualizado'
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
        document.querySelector('#fecha').value = "";
        document.querySelector('#select_pacientes').value = null;
        document.querySelector('#select_odontologos').value = null;
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