var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/',function (req, res) {
    return res.send({ error: true,message: 'Test Student Web API'})
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'final_pj'
});

dbConn.connect();

app.get('/allstd', function (req, res) {
    dbConn.query('SELECT * FROM student' , function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/std', function (req, res){
    var std = req.body
    if (!std) {
        return res.status(400).send({ error:true, message: 'Please provide student'});
    }
    dbConn.query("INSERT INTO student SET ? ", std , function (error, results, fields){
        if (error) throw error;
        return res.send(results);
    });
});

app.get('/std/:id', function (req, res) {
  
    let Book_id = req.params.id;
  
    if (!Book_id) {
        return res.status(400).send({ error: true, message: 'Please provide Book_id' });
    }
  
    dbConn.query('SELECT * FROM student where Book_id=?', Book_id , function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    });
});

app.put('/std/:id', function (req, res) {
  
    let Book_id = req.params.id;
    let std = req.body;
  
    if (!Book_id || !std ) {   
        return res.status(400).send({ error: user, message: 'Please provide student data and student_id' });    
    }
  
        dbConn.query("UPDATE student SET ? WHERE Book_id = ?", [std, Book_id], function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Student has been updated successfully.' });
    });
});

app.delete('/std/:id', function (req, res) {
  
    let Book_id = req.params.id;
  
    if (!Book_id) {
        return res.status(400).send({ error: true, message: 'Please provide Book_id' });
    }
    dbConn.query('DELETE FROM student WHERE Book_id = ?', Book_id, function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Student has been deleted successfully.' });
    });
}); 



//////////////borrow////////////////

app.get('/allborrow', function (req, res) {
    dbConn.query('SELECT * FROM borrow' , function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

app.post('/borrow', function (req, res){
    var borrow = req.body
    if (!borrow) {
        return res.status(400).send({ error:true, message: 'Please provide student'});
    }
    dbConn.query("INSERT INTO borrow SET ? ", borrow , function (error, results, fields){
        if (error) throw error;
        return res.send(results);
    });
});

app.get('/borrow/:id', function (req, res) {
  
    let std_id = req.params.id;
  
    if (!std_id) {
        return res.status(400).send({ error: true, message: 'Please provide std_id' });
    }
  
    dbConn.query('SELECT * FROM borrow where std_id=?', std_id , function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    });
});

app.put('/borrow/:id', function (req, res) {
  
    let std_id = req.params.id;
    let borrow = req.body;
  
    if (!std_id || !borrow ) {   
        return res.status(400).send({ error: user, message: 'Please provide student data and student_id' });    
    }
  
        dbConn.query("UPDATE borrow SET ? WHERE std_id = ?", [borrow, std_id], function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Student has been updated successfully.' });
    });
});

app.delete('/borrow/:id', function (req, res) {
  
    let std_id = req.params.id;
  
    if (!std_id) {
        return res.status(400).send({ error: true, message: 'Please provide std_id' });
    }
    dbConn.query('DELETE FROM borrow WHERE std_id = ?', std_id, function (error, results, fields) {
        if (error) throw error;
        return res.send({ error: false, data: results, message: 'Student has been deleted successfully.' });
    });
}); 



app.listen(3000, function (){
    console.log('Node app is running on port 3000');
});


module.exports = app;





