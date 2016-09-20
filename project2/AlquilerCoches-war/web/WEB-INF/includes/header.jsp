<%-- 
    Document   : header
    Created on : 28-mar-2014, 1:00:48
    Author     : malkomich
--%>

<div class="header">
    <div style="float: left">
        <svg>
        <circle id="circle"
                cx="80" cy="80" r="50"
                fill="white" stroke=#000770 stroke-width="6"/>
        <line id="hours"
              x1="80" y1="80" x2="110" y2="80"
              style="stroke:#5198c4;stroke-width:5"/>
        <line id="minutes"
              x1="80" y1="80" x2="80" y2="40"
              style="stroke:#5198c4;stroke-width:3"/>
        <line id="seconds"
              x1="80" y1="80" x2="115" y2="45"
              style="stroke:grey;stroke-width:1"/>
        </svg>
    </div>
    <div class="tabs_bar">
        <ul>
            <li><a href="admin">Empleado</a></li>
            <li><a href="logout">Salir</a></li>
        </ul>
    </div>
</div>