import React from "react";

const NewGroup = ({onClick}) => (
    <div className="newGroup" onClick={onClick}>
        <div className="content">
            <div className="circle">
                <span>+</span>
            </div>
            <span>New Channel</span>
        </div>
    </div>
);

export default NewGroup;
