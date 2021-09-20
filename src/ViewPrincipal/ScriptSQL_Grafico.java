package ViewPrincipal;

public class ScriptSQL_Grafico {
    /**
   SELECT 
		CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha,
		COUNT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) ) AS 'CANT VEHÍCULOS'
  FROM [costos_vg].[dbo].[mvto_carbon] 
		INNER JOIN [costos_vg].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]
		INNER JOIN [costos_vg].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]
		INNER JOIN [costos_vg].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]
		INNER JOIN [costos_vg].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]
  WHERE mc_estad_mvto_carbon_cdgo=1 AND [zt_cdgo]=2 AND mc_cliente_cdgo='297' AND mc_articulo_cdgo='2425' AND [mc_fecha_inicio_descargue] BETWEEN '2021-06-01 07:48:03.623' AND '2021-06-30 15:07:20.063'
  GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]))

SELECT 
		DISTINCT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]))) as Fecha
		--,COUNT (CONCAT(YEAR([mc_fecha]),'-',MONTH([mc_fecha]), '-', Day([mc_fecha])) )
		,(SUM([mc_peso_neto]))AS 'KG'
		,(SUM([mc_peso_neto])/1000)AS 'TONELADAS'
		,(SUM([mc_peso_neto])/CAST(1000 AS FLOAT))AS 'TONELADAS'
  FROM [costos_vg].[dbo].[mvto_carbon] 
		INNER JOIN [costos_vg].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]
		INNER JOIN [costos_vg].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]
		INNER JOIN [costos_vg].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]
		INNER JOIN [costos_vg].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]
  WHERE mc_estad_mvto_carbon_cdgo=1 AND [mc_cntro_oper_cdgo]= 1 AND  [zt_cdgo]=2 AND mc_cliente_cdgo='297' AND mc_articulo_cdgo='2425' AND [mc_fecha_inicio_descargue] BETWEEN '2021-06-01 07:48:03.623' AND '2021-06-30 15:07:20.063'
  GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]))

    --SELECT CONCAT('',YEAR(GETDATE()),'-',MONTH(GETDATE()),'-',DAY(GETDATE()),' 00:00:00.000 AND ',YEAR(GETDATE()),'-',MONTH(GETDATE()),'-',DAY(GETDATE()), ' 23:59:99.000')
    -- SELECT CONVERT (date, GETDATE());
     
     **/
}
