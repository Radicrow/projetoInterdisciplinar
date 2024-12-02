from pickle import STRING

import psycopg2
import pandas as pd
import numpy as np
import scipy
import openpyxl as xl



pd.options.display.width = 0
pd.set_option('display.max_rows', None)

conexao = psycopg2.connect(
    database='algoritmo',
    host='localhost',
    user='postgres',
    password='1234',
    port=5432
)

cur = conexao.cursor()

cur.execute("SELECT * FROM hardware;")

hardwares = cur.fetchall()

#print(hardwares)

cur.execute("SELECT * FROM resultados_v;")

resultados = cur.fetchall()

colunas = [desc[0] for desc in cur.description]  # Obtém os nomes das colunas
df_geral = pd.DataFrame(resultados, columns=colunas)
df_geral['Intervalos de confiança'] = None
df_geral['Média'] = None
df_geral['Desvio padrão'] = None
#print(df_geral)

df_quick = df_geral[df_geral['nome_algoritmo'] == 'Quick Sort']
df_merge = df_geral[df_geral['nome_algoritmo'] == 'Merge Sort']
df_shell = df_geral[df_geral['nome_algoritmo'] == 'Shell Sort']
df_bubble = df_geral[df_geral['nome_algoritmo'] == 'Bubble Sort']

df_quick_1_cenario_10000 = df_quick[(df_quick['tamanho_amostra'] == 10000) & (df_quick['cenario'] == 1)]

#print(df_quick_1_cenario_10000)


#print(intervalo)

def calculosEstastisticos(df_generico, algoritmo):
    valores = [10000, 100000, 500000]
    cenarios = [1, 2, 3, 4]

    for valor in valores:
        for cenario in cenarios:
            df_generico_modificado = df_generico[(df_generico['cenario'] == cenario) & (df_generico['tamanho_amostra'] == valor)]


            media = df_generico_modificado['tempo_execucao_microsegundos'].mean()
            desvio_padrao = df_generico_modificado['tempo_execucao_microsegundos'].std(ddof=1)
            total = len(df_generico_modificado['tempo_execucao_microsegundos'])

            conf = 0.95
            alpha = 1 - conf

            t_critico = scipy.stats.t.ppf(1 - alpha / 2, df=total - 1)

            margem_erro = t_critico * (desvio_padrao / np.sqrt(total))

            intervalo = (media - margem_erro, media + margem_erro)

            intervalo = str(round(intervalo[0], 3)) + ' a ' + str(round(intervalo[1], 3))

            df_geral.loc[(df_geral['cenario'] == cenario) & (df_geral['tamanho_amostra'] == valor) & (df_geral['nome_algoritmo'] == algoritmo), 'Intervalos de confiança'] = intervalo
            df_geral.loc[(df_geral['cenario'] == cenario) & (df_geral['tamanho_amostra'] == valor) & (df_geral['nome_algoritmo'] == algoritmo), 'Média'] = round(media, 3)
            df_geral.loc[(df_geral['cenario'] == cenario) & (df_geral['tamanho_amostra'] == valor) & (df_geral['nome_algoritmo'] == algoritmo), 'Desvio padrão'] = round(desvio_padrao, 3)

calculosEstastisticos(df_quick, 'Quick Sort')
calculosEstastisticos(df_merge, 'Merge Sort')
calculosEstastisticos(df_shell, 'Shell Sort')
calculosEstastisticos(df_bubble, 'Bubble Sort')

excel_file_path = "Excel/dados-tratados.xlsx"
df_geral.to_excel(excel_file_path, index=False)
print(f"Arquivo Excel salvo em: {excel_file_path}")