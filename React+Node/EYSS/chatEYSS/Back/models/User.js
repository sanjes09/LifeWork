const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");
const {Schema} = mongoose;

const UserSchema = new Schema({
    username: {type: String, required: "Username no puede estar vacio."},
    email: {type: String, required: "Email no puede estar vacia."},
    password: {type: String, required: "Password no puede estar vacia."}
});

UserSchema.methods.encryptPassword = async (password) => {
    const salt = await bcrypt.genSalt(10);
    const hash = bcrypt.hash(password,salt);
    return hash;
};

UserSchema.methods.matchPassword = async function (password) {
    return await bcrypt.compare(password, this.password);
};

module.exports = mongoose.model("User", UserSchema);