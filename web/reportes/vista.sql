CREATE OR REPLACE VIEW vistalogica AS 
 SELECT clientes.nit,
    clientes.digitochequeo,
    clientes.nombre,
    ( SELECT ciius.nombre
           FROM ciius
          WHERE ciius.id::text = clientes.ciiu::text) AS ciius,
    clientes.rating,
    ( SELECT bancas.nombre
           FROM bancas
          WHERE bancas.id::text = clientes.banca::text) AS banca,
    ( SELECT estados.nombre
           FROM estados
          WHERE estados.id::text = clientes.estado::text) AS estado,
    clientes.desde,
    clientes.gestorcomercial,
    clientes.fecharating,
    ( SELECT estados.nombre
           FROM estados
          WHERE estados.id::text = clientes.estadocliente::text) AS estadocliente,
    clientes.valoractivo,
    clientes.fechabalance,
    ( SELECT grupos_economicos.nombre
           FROM grupos_economicos
          WHERE grupos_economicos.id::text = clientes.grupo::text) AS grupo
   FROM clientes;