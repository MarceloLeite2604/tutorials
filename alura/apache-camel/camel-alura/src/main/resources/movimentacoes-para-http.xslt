<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:alura="http://alura.com.br">

	<xsl:template match="/movimentacoes">
		<html xmlns:alura="http://alura.com.br">
			<body>
				<table>
					<tr>
						<th>Valor</th>
						<th>Data</th>
						<th>Tipo</th>
					</tr>
					<xsl:for-each select="/movimentacoes/movimentacao">
						<tr>
							<td>
								<xsl:value-of select="valor" />
							</td>
							<td>
								<xsl:value-of select="data" />
							</td>
							<td>
								<xsl:value-of select="tipo" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>