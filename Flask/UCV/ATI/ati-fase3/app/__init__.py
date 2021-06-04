import os
from flask import Flask, redirect, render_template, request, session, url_for
from pymongo import MongoClient
from bson.objectid import ObjectId
from werkzeug.utils import secure_filename
app = Flask (__name__, template_folder='templates', static_folder = 'static')
app.secret_key = "falcs_rules"
db = MongoClient('mongodb://35.225.24.45:27017').falcs	

MyUser = ""

#TABLAS
Users = db.users
Categories = db.categories
Rules = db.rules

#Home
@app.route("/admin")
def admin():
	if session :
		if session["type"] == "admin" :
			return render_template('home-admin.html')
		else :
			return redirect(url_for("index"))
	else :
		return redirect(url_for("login"))

#Home
@app.route("/")
def index():
	# Users.insert({"type":"admin", "name": "Jesus Santiago", "username": "SantiagoAdmin", "email": "sanjes099@gmail.com", "password": "jesan190994", "img": ""})
	if session :
		if session["type"] == "user":
			trivias = Categories.find({})
			return render_template('trivias.html', trivias=trivias)
		else:
			return redirect(url_for("admin"))
	else :
		return redirect(url_for("login"))

#Login
@app.route("/login", methods=['GET','POST'])
def login():
	if request.method == "GET" :
		return render_template('login.html')
	else : 
		username = request.form['user']
		password = request.form['pass']
		user = Users.find_one({ "username" : username, "password" : password})
		if (user) :
			session["id"]=str(user["_id"])
			session["username"]=user["username"]
			session["type"]=user["type"]
			if user["type"]=="user":
				return redirect(url_for('index'))
			else :
				return redirect(url_for('admin'))
		else :
			return render_template('login.html', alert="User or Password incorrect", stl="alert-danger")
	
#Logout
@app.route("/logout")
def logout():
	session.clear()
	return redirect(url_for("login"))

#Register
@app.route("/register", methods=['GET','POST'])
def register():
	if request.method == "GET" :
		return render_template('register.html')
	else :
		name = request.form['name']
		email = request.form['email']
		username = request.form['user']
		password = request.form['pass']
		#validaciones
		usuario = Users.find_one({"username" : username})
		if(usuario) : 
			return render_template('register.html', alert="Username already exists", stl="alert-danger")
			
		correo = Users.find_one({"email" : email})
		if(correo) :
			return render_template('register.html', alert="Email already exists", stl="alert-danger")

		log = Users.insert({"type":"user", "name": name, "username": username, "email": email, "password": password, "img": "", "points": "0", "ranking": "0"})
		if (log) :
			return render_template('login.html', alert="Successfully registred", stl="alert-success")
		else :
			return render_template('register.html', alert="Error", stl="alert-danger")

#Forget Password
@app.route("/forgot-pass")
def forgot():
	return render_template('forgot-pass.html')

#Login With Facebook
@app.route("/facebookRegister", methods=['POST'])
def facebookRegister():
	name = request.form['name']
	email = request.form['email']
	picture = request.form['picture']
	username = ""
	password = ""
	
	#busco al user
	user = Users.find_one({"email" : email})
	#si existe inserto todo en sesion y redirijo a home
	if(user) : 
		session["id"]=str(user["_id"])
		session["username"]=user["username"]
		session["type"]=user["type"]
		return "index"
	else :
		#si no existe lo creo
		log = Users.insert({"type": "user", "name": name, "username": "", "email": email, "password": "", "img": picture, "points": "0", "ranking": "0"})
		if (log) :
			# lo redirigo a la pantalla que va a crear carlos pasando el user
			user = Users.find_one({"email" : email})
			session["id"]=str(user["_id"])
			session["type"]="user"
			return "username"

#Username Facebook
@app.route("/usernameFacebook", methods=['GET','POST'])
def facebookUsername():
	if request.method == "GET" :
		return render_template('UsernameFB.html')
	else :
		#busco al user
		user = Users.find_one({"_id" : ObjectId(session["id"])})
		user["username"] = request.form['username']
		session["username"]=user["username"]
		Users.update_one({"_id":ObjectId(session["id"])},{"$set":user})
		return redirect(url_for('index'))

#History
@app.route("/history")
def history():
	return render_template('history.html', is_admin = False)

#History
@app.route("/history-admin")
def history_admin():
	return render_template('history.html', is_admin = True)

#UsernameFB
@app.route("/facebook")
def facebook():
	return render_template('UsernameFB.html', user = "admin")

#Reglas
@app.route("/reglas")
def reglas():
	rules = Rules.find({})
	return render_template('reglas.html', rules=rules)

#Modificar Reglas
@app.route("/modificarRegla", methods=['GET','POST'])
def modificarRegla():
	if session :
		if request.method == "GET" :
			rules = Rules.find({})
			return render_template('modificarRegla.html', rules=rules)
		else :
			regla = Rules.find_one({"_id": ObjectId(request.form["id"])})
			regla["title"] = request.form["title"]
			regla["description"] = request.form["description"]
			Rules.update_one({"_id":regla["_id"]},{"$set":regla})
			return redirect(url_for("modificarRegla"))

	else :
		return redirect(url_for("login"))
	
#Eliminar Reglas
@app.route("/eliminarRegla", methods=['POST'])
def eliminarRegla():
	if session :
			Rules.remove({"_id": ObjectId(request.form["id"])})
			return redirect(url_for("modificarRegla"))
	else :
		return redirect(url_for("login"))

#crear Reglas
@app.route("/crearRegla", methods=['POST'])
def crearRegla():
	if session :
			Rules.insert({"title": request.form["title"] , "description": request.form["content-rule"]})
			return redirect(url_for("modificarRegla"))
	else :
		return redirect(url_for("login"))

#Trivia
@app.route("/trivia/<id>")
def trivia(id):
	if session :
		trivia = Categories.find_one({"_id": ObjectId(id)})
		return render_template('trivia.html', trivia=trivia)
	else :
		return redirect(url_for("login"))

#Crear Trivia
@app.route("/crearTrivia", methods=['GET','POST'])
def crearTrivia():
	if session :
		if session["type"] == "admin" :
			if request.method == "GET" :
				return render_template('crearTrivia.html')
			else :
				fondo = request.files['img']
				filename = secure_filename(fondo.filename)
				fondo.save(app.root_path+"/static/img/categories/"+filename)
				
				catName = request.form["name"]
				catFondo = "/static/img/categories/"+filename
				Categories.insert({"name": catName, "img": catFondo, "points": "0"})
				return redirect(url_for("trivias_admin"))
		else :
			return redirect(url_for("index"))
	else :
		return redirect(url_for("login"))

#Modificar Trivia
@app.route("/modificarTrivia", methods=['POST'])
def modificarTrivia():
	if session :
		if session["type"] == "admin" :
			# if request.method == "GET" :
			# 	return render_template('modificarTrivia.html')
			# else :
			cat = Categories.find_one({"_id": ObjectId(request.form["id"])})
			print(request.files)
			if request.files["file"].filename != "" :
				fondo = request.files['file']
				filename = secure_filename(fondo.filename)
				fondo.save(app.root_path+"/static/img/categories/"+filename)
				os.remove(app.root_path+cat["img"])
				cat["img"] = "/static/img/categories/"+filename

			cat["name"] = request.form["name"]
			Categories.update_one({"_id":cat["_id"]},{"$set":cat})

			return redirect(url_for("trivias_admin"))
		else :
			return redirect(url_for("index"))
	else :
		return redirect(url_for("login"))

#Eliminar Trivia
@app.route("/eliminarTrivia", methods=['POST'])
def eliminarTrivia():
	if session :
		if session["type"] == "admin" :
			Categories.remove({"_id": ObjectId(request.form["id"])})
			return redirect(url_for("trivias_admin"))
		else :
			return redirect(url_for("index"))
	else :
		return redirect(url_for("login"))

#views
@app.route("/profile/<id>", methods=['GET','POST'])
def profile(id):
	if session :
		if request.method == "GET" :
			user = Users.find_one({ "_id" : ObjectId(id)})
			if user :
				if session["id"] == id :
					return render_template('profile.html', user=user, myprofile = True)
				else :
					return render_template('profile.html', user=user, myprofile = False)
		else : 
			MyUser = Users.find_one({ "_id" : ObjectId(session["id"])})

			if request.form['username']:
				MyUser["username"] = request.form['username']
			if request.form['name']:
				MyUser["name"] = request.form['name']
			if request.form['email']:
				MyUser["email"] = request.form['email']
			if request.form['pass']:
				MyUser["password"] = request.form['pass']

			if request.files['file'].filename != "" : 
				file = request.files['file']
				filename = secure_filename(file.filename)
				file.save(app.root_path+"/static/img/profile/"+filename)
				if(MyUser["img"] != ""):
					os.remove(app.root_path+MyUser["img"])
				MyUser["img"] = "/static/img/profile/"+filename

			Users.update_one({"_id":MyUser["_id"]},{"$set":MyUser})
			return redirect(url_for('profile',id=id))
	else :
		return redirect(url_for("login"))

#Buscar usuarios
@app.route("/search-user", methods=['GET','POST'])
def search_user():
	if session :
		if request.method == "GET" :
			allUsers = Users.find({"type":"user"})
			return render_template('search-user.html', allUsers = allUsers)
		else : 
			username = request.form['user']
			thisuser = Users.find_one({ "username" : username})
			print(thisuser)
			return render_template('search-user.html', oneUser = thisuser)
	else :
		return redirect(url_for("login"))

#Change user type
@app.route("/changeType/<id>/<typee>", methods=['POST'])
def changeType(id,typee):
	
	changeUser = Users.find_one({ "_id" : ObjectId(id)})
	if typee == "admin":
		changeUser["type"]="user"
	else:
		changeUser["type"]="admin"

	Users.update_one({"_id":changeUser["_id"]},{"$set":changeUser})		
	return redirect(url_for("profile", id=id))

#Ver trivias admin
@app.route("/trivias-admin")
def trivias_admin():
	if session :
		if session["type"] == "admin" :
			trivias = Categories.find({})
			return render_template('trivias-admin.html', trivias=trivias)
		else :
			return redirect(url_for("index"))
	else :
		return redirect(url_for("login"))

#Agregar preguntas
@app.route("/addPregunta", methods=['POST'])
def addPregunta():
	if session :
		if session["type"] == "admin" :
			return redirect(url_for("index"))
		else :
			return redirect(url_for("index"))
	else :
		return redirect(url_for("login"))

#Sorteos
@app.route("/draws-list")
def sorteos():
	return render_template('sorteos.html', is_admin = True, description_sorteo = "Carro Chevrolet", status_sorteo = "Entregado", position_sorteo = "1er", date_sorteo = "23/09/2019")

#Sorteos
@app.route("/draws-admin")
def sorteos_admin():
	return render_template('sorteos.html', is_admin = True, description_sorteo = "Carro Chevrolet", status_sorteo = "Entregado", position_sorteo = "1er", date_sorteo = "23/09/2019", user = "admin")

#Premios instantaneos
@app.route("/snapshots-list")
def snapshots():
	return render_template('snapshots.html', is_admin = True, points_snapshots = "100", frecuencia = "3 trivias", cantidad = "20", date_snapshots = "23/09/2019")

#Premios instantaneos
@app.route("/snapshots-admin")
def snapshots_admin():
	return render_template('snapshots.html', is_admin = True, points_snapshots = "100", frecuencia = "3 trivias", cantidad = "20", date_snapshots = "23/09/2019", user = "admin")

if __name__ == "__main__":
	app.run(debug=True)
