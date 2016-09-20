/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
    function showTime() {
        var date = new Date();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        
        $('#seconds').attr('x2', x2(second,60,80,50)).attr('y2', y2(second,60,80,50));
        $('#minutes').attr('x2', x2(minute,60,80,50)).attr('y2', y2(minute,60,80,50));
        $('#hours').attr('x2', x2(hour,60,80,50)).attr('y2', y2(hour,60,80,50));
    }
    setInterval(function(){showTime();}, 1000);
    showTime();
});

function x2(n,i,x1,r) {return x1 + r*Math.sin(2*Math.PI*n/i);};
function y2(n,i,y1,r) {return y1 - r*Math.cos(2*Math.PI*n/i);};

function defaultForm(form){
    if(validar(form))
        form.submit();
}

function ajaxForm(form){
    if(validar(form))
        peticionAjax(form);
}

function validar(form) {
    for(i=0; i<form.elements.length; i++){
        var element = form.elements[i];
        if(element.type === 'text' || element.type === 'password'){
            if(alfanumerico(element.value) === 0){
                alert("Debe rellenar todos los campos.");
                element.focus();
                return false;
            }
        }else if(element.type === 'date'){
            if(isDate(element.value) === -1){
                alert("Introduce una fecha correcta.");
                element.focus();
                return false;
            } else if(isDate(element.value) === -2){
                alert("La fecha introducida no puede ser anterior a hoy.");
                element.focus();
                return false;
            }
        }
    }
    return true;
}

function alfanumerico(txt) {
    // Devuelve 0 si la cadena esta vacia, 1 si es numerica 
    //o 2 si es alfanumerica
    var i;
    if (txt.length!==0) {
        for (i=0;i<txt.length;i++){
            if (txt.substr(i,1)<"0" || txt.substr(i,1)>"9") {
                return 2;
            }
        }
        return 1;
    }
    else
        return 0;
}

function isDate(date){
    var  patron = /^\d{4}\-\d{2}\-\d{2}$/;
    if (!date.match(patron) || date ==='' ){
        return -1;
    }
    var today = new Date();
    if(date<today){
        return -2;
    }
    return 0;
}

function peticionAjax(form){
    switch(form.accion.value){
        case "Buscar Reservas":
            $('.validationMessage').hide();
            $.getJSON("recogida", { accion:form.accion.value, cliente:form.cliente.value }, function(response){
                if(isEmptyJSON(response)){
                    alert("Introduzca un NIF de cliente válido por favor.");
                } else{
                    if(isEmptyJSON(response.reservas)){
                        $('.contenedorReservas').hide();
                        $('.validationMessage').fadeIn('slow');
                    } else{
                        $('.contenedorReservas > table tr.reg').remove();
                        $('.contenedorReservas').show();
                        $.each(response.reservas, function(){
                            $('.contenedorReservas > table').append('<tr class="reg" onclick="activateReg(this)"><td>' + this.matricula + '</td><td>' + this.fechaFin + '</td><td>' + this.modelo + '</td><td>' + this.marca + '</td></tr>');
                            $('.inputContainer').append('<input type="radio" name="regReserva" value="' + this.idReserva + '">');
                        });
                    }
                }
            });
            break;
        case "Crear Alquiler":
            var regReserva = $('input[name="regReserva"]:checked').val();
            $.post("recogida", { accion:form.accion.value, kilometraje:form.kilometraje.value, idReserva:regReserva }, function(){
               alert("Alquiler creado con éxito.");
            });
            break;
        case "Mostrar":
            $('.validationMessage').hide();
            $.getJSON("devolucion", { accion:form.accion.value, vehiculo:form.vehiculo.value }, function(response){
                if(isEmptyJSON(response)){
                    alert("Introduzca una matrícula válida por favor.");
                } else{
                    if(isEmptyJSON(response.alquiler)){
                        $('.contenedorDetalles').hide();
                        $('.validationMessage').fadeIn('slow');
                    } else{
                        $('.contenedorDetalles > table tr.reg').remove();
                        $('.contenedorDetalles > table').append('<tr class="reg" onclick="activateReg(this)"><td>' + response.alquiler[0].nombreCliente + '</td><td>' + response.alquiler[0].nifCliente + '</td><td>' + response.alquiler[0].fechaInicio + '</td><td>' + response.alquiler[0].fechaFin + '</td><td>' + response.alquiler[0].matricula + '</td><td>' + response.alquiler[0].kilometrajeSalida + '</td></tr>');
                        $('.contenedorDetalles').show();
                        $('.inputContainer').append('<input type="radio" name="regAlquiler" value="' + response.alquiler[0].idAlquiler + '">');
                    }
                }
            });
            break;
        case "Devolucion":
            $.post("devolucion", { accion:form.accion.value, kilometraje:form.kilometraje.value }, function(){
               alert("Devolución registrada con éxito.");
            });
            break;
    }
}

function isEmptyJSON(obj) {
  for(var i in obj) { return false; }
  return true;
}

function activateReg(element){
    $(element).siblings().removeClass("regSelected").addClass("reg");
    $(element).addClass("regSelected");
    var index = $(element).index();
    $('.inputContainer').find('input:radio').attr('checked', false);
    $('.inputContainer').find('input:radio').eq(index-2).attr('checked', true);
}

function registrar(form) {
    var km;
    do{
        km = prompt("Introduce el kilometraje del vehículo", "0");
    }while (!validaFloat(km));
    if(km !== null){
        $('input[name="kilometraje"]').val(km);
        peticionAjax(form);
    }
}

function validaFloat(numero){
    if (!/^([0-9])*[.]?[0-9]*$/.test(numero)){
        alert("El valor " + numero + " no es un número");
        return false;
    }
    return true;
}