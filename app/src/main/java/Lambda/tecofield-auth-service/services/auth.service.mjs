import crypto from "crypto";
import * as repo from "../repositories/auth.repository.mjs";

const SECRET = "mi_secreto_super_seguro_para_gestor_tareas";

export const registro = async (data) => {
  // Crear una "sal" y encriptar la contraseña
  const salt = crypto.randomBytes(16).toString('hex');
  const hash = crypto.pbkdf2Sync(data.password, salt, 1000, 64, 'sha512').toString('hex');

  const nuevoUsuario = {
    email: data.email,
    nombre: data.nombre,
    hash,
    salt
  };

  await repo.insertarUsuario(nuevoUsuario);
  return { mensaje: "Registro exitoso", email: data.email };
};

export const login = async (data) => {
  const result = await repo.getUsuarioByEmail(data.email);
  const user = result.Item;

  if (!user) throw new Error("Usuario no existe");

  // Verificar la contraseña usando la misma "sal"
  const hashCalculado = crypto.pbkdf2Sync(data.password, user.salt, 1000, 64, 'sha512').toString('hex');
  
  if (hashCalculado !== user.hash) {
    throw new Error("Contraseña incorrecta");
  }

  // Generar un Token JWT nativo
  const header = Buffer.from(JSON.stringify({ alg: "HS256", typ: "JWT" })).toString('base64url');
  const payload = Buffer.from(JSON.stringify({ email: user.email, nombre: user.nombre })).toString('base64url');
  const signature = crypto.createHmac('sha256', SECRET).update(`${header}.${payload}`).digest('base64url');
  const token = `${header}.${payload}.${signature}`;

  return { token, nombre: user.nombre, email: user.email };
};