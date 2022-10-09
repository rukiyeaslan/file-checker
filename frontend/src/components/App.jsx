import React, { useState ,useEffect} from "react";
import Alert from "./Alert";
//import axios from "axios";
const axios = require('axios').default;

axios.defaults.baseURL ="http://localhost:9090";
//axios.defaults.withCredentials = true;

//not sure if the line above should remain
function App() {
    const [file, setFile] = useState()
    const [fileName, setFileName] = useState("");
    const [success, setSuccess] = useState(false);
    const [error, setError] = useState(false);

    const [show, setShow] = useState(false)

    // On componentDidMount set the timer
    useEffect(() => {
      const timeId = setTimeout(() => {
        // After 3 seconds set the show value to false
        setShow(false)
        setError(false)
        setSuccess(false)
      }, 3000)
  
      return () => {
        clearTimeout(timeId)
      }
    }, [success,error]);

    const handleSubmit = (e) => {
        
        const formData = new FormData();
        formData.append("file", file);
        console.log(file);
        axios
          .post('api/v1/file', formData)
          .then((res) => {
            console.log(res);
            setSuccess(true);
            setShow(true)
           })
          .catch((err) => {
            setError(true);
            setShow(true)
           // alert(err)  
          });
      };

   function handleFile(event) {
        setFile(event.target.files[0])
    }

    return (
      <>
      {show && success && <Alert type = "success" message="File upload is successful!"></Alert>  }
      {show && error && <Alert type = "error" message="File contains malicious content. File upload is not successful!"></Alert>}
      <div className="container">
        <h1>Upload file</h1>
        <input
          onChange={handleFile}
          type="file"
        />
        
        
        <button onClick={handleSubmit}>Submit</button>
      </div>
      </>
    );
}

export default App;