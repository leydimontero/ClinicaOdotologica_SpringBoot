window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontologos del JSON
       console.log(data)
         for(turno of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("turnosTable");
            var turnoRow = table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una pelicula
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&#128465' +
                                      '</button>';

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_update_' + turno.id + '\"' +
                                      ' type="button" onclick="updateTurno('+turno.id+')" class="btn btn-info btn_id">' +
                                      '&#9998' +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            turnoRow.innerHTML = '<td>' + turno.id + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                    '<td class=\"td_odontologo\">' + turno.odontologoId + '</td>' +
                    '<td class=\"td_paciente\">' + turno.pacienteId + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };

    })
    })


    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_all_turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })

    function updateTurno(id) {
        window.location.href = `post_update_turnos.html?id=${id}`;
    }

    function deleteBy(id)
    {
              //con fetch invocamos a la API de odontologos con el método DELETE
              //pasandole el id en la URL
              const url = '/turnos/'+ id;
              const settings = {
                  method: 'DELETE'
              }
              fetch(url,settings)
              .then(response => response.json())

              //borrar la fila de un odontologo eliminada
              let row_id = "#tr_" + id;
              document.querySelector(row_id).remove();

    };

