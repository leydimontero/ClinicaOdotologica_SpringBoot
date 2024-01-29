window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontologos del JSON

         for(odontologo of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("odontologosTable");
            var odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una pelicula
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                      ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                      '&#128465' +
                                      '</button>';

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_update_' + odontologo.id + '\"' +
                                      ' type="button" onclick="updateOdontologo('+odontologo.id+')" class="btn btn-info btn_id">' +
                                      '&#9998' +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            odontologoRow.innerHTML = '<td>' + odontologo.id + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    })


    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_all_odontologos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })

    function updateOdontologo(id) {
        window.location.href = `post_update_odontologos.html?id=${id}`;
    }

    function deleteBy(id)
    {
              //con fetch invocamos a la API de odontologos con el método DELETE
              //pasandole el id en la URL
              const url = '/odontologos/'+ id;
              const settings = {
                  method: 'DELETE'
              }
              fetch(url,settings)
              .then(response => response.json())

              //borrar la fila de un odontologo eliminada
              let row_id = "#tr_" + id;
              document.querySelector(row_id).remove();

    };

