import * as service from "../services/auth.service.mjs";
import { registroSchema, loginSchema } from "../validators/auth.schema.mjs";
import { ok, created, badRequest, internalError } from "../utils/response.mjs";

export const registrarUsuario = async (event) => {
  const body = typeof event.body === 'string' ? JSON.parse(event.body) : event.body;
  const parsed = registroSchema.safeParse(body);

  if (!parsed.success) return badRequest(parsed.error);

  try {
    const res = await service.registro(parsed.data);
    return created(res);
  } catch (error) {
    return internalError(error.message);
  }
};

export const loginUsuario = async (event) => {
  const body = typeof event.body === 'string' ? JSON.parse(event.body) : event.body;
  const parsed = loginSchema.safeParse(body);

  if (!parsed.success) return badRequest(parsed.error);

  try {
    const res = await service.login(parsed.data);
    return ok(res);
  } catch (error) {
    // Si la contraseña es incorrecta o el usuario no existe, devolvemos un 400
    return badRequest({ message: error.message });
  }
};