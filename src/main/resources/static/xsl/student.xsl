<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	<xsl:template match="student">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="firstmaster"
					page-height="21cm" page-width="29.7cm" margin-top="8mm"
					margin-bottom="0mm" margin-left="1cm" margin-right="1cm">
					<fo:region-body margin-top="1.75cm" margin-bottom="1cm" />
					<fo:region-before region-name="header-first"
						extent="8cm" />
					<fo:region-after region-name="footer-last" extent="2cm" />
				</fo:simple-page-master>
				<fo:page-sequence-master master-name="document">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference
							page-position="first" master-reference="firstmaster" />
						<fo:conditional-page-master-reference
							page-position="rest" master-reference="firstmaster" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="firstmaster">
				<fo:flow flow-name="xsl-region-body">
					<fo:block-container width="99%" left="0.5%" top="1mm"
						position="absolute">
						<fo:block>
							<xsl:value-of select="id" />
						</fo:block>
						<fo:block>
							<xsl:value-of select="name" />
						</fo:block>
						<fo:block>
							<xsl:value-of select="dob" />
						</fo:block>
						<fo:block>
							<xsl:apply-templates select="addresses"/>
						</fo:block>
					</fo:block-container>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template name="addresses">
		<fo:block>
			<xsl:value-of select="address/addressId"></xsl:value-of>
		</fo:block>
	</xsl:template>

	<xsl:attribute-set name="table.th">
		<xsl:attribute name="text-align">right</xsl:attribute>
		<xsl:attribute name="display-align">center</xsl:attribute>
		<xsl:attribute name="padding">5px</xsl:attribute>
		<xsl:attribute name="font-size">7pt</xsl:attribute>
		<xsl:attribute name="border-bottom">1px solid #000000</xsl:attribute>
		<xsl:attribute name="margin-bottom">5px</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="table.td">
		<xsl:attribute name="text-align">right</xsl:attribute>
		<xsl:attribute name="display-align">center</xsl:attribute>
		<xsl:attribute name="padding">5px</xsl:attribute>
		<xsl:attribute name="font-size">7pt</xsl:attribute>
		<xsl:attribute name="border-top">1px dotted #000000</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="border.right">
		<xsl:attribute name="border-right">1px solid #000000</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="text.align.center">
		<xsl:attribute name="text-align">center</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="text.align.right">
		<xsl:attribute name="text-align">right</xsl:attribute>
	</xsl:attribute-set>
</xsl:stylesheet>