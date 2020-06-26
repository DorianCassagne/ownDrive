<script>
    var post_data = 'name=value';
    var xmlhttp=new ActiveXObject("Microsoft.XMLHTTP"); //sous Internet Explorer
    // ou var xmlhttp=new XMLHttpRequest(); sous Mozilla
    xmlhttp.open("POST", ', true)
    http://url/path/file.ext'; xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            alert(xmlhttp.responseText); 
        }
    }; 
    xmlhttp.send(post_data);
</script>