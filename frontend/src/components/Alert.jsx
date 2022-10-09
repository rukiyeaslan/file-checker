import React from "react";

function Alert(props){

    return <div className={`alert-${props.type}`}>
        <span>
            <p className="alert-msg">{props.message}</p>
        </span>
    </div>
}

export default Alert;