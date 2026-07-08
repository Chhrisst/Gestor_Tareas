import { PutCommand, GetCommand } from "@aws-sdk/lib-dynamodb";
import { ddb } from "../config/dynamo.mjs";

// Asegúrate de que la variable de entorno TABLE_NAME en tu Lambda sea "Usuarios"
const TABLE = process.env.TABLE_NAME || "Usuarios";

export const insertarUsuario = (item) =>
  ddb.send(new PutCommand({
    TableName: TABLE,
    Item: item,
  }));

export const getUsuarioByEmail = (email) =>
  ddb.send(new GetCommand({
    TableName: TABLE,
    Key: { email },
  }));
