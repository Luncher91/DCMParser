package net.alenzen.dcm;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import net.alenzen.dcm.antlr.dcmLexer;
import net.alenzen.dcm.antlr.dcmParser.Array_expContext;
import net.alenzen.dcm.antlr.dcmParser.Array_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Characteristic_line_expContext;
import net.alenzen.dcm.antlr.dcmParser.Characteristic_line_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Characteristic_map_expContext;
import net.alenzen.dcm.antlr.dcmParser.Characteristic_map_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Dcm_fileContext;
import net.alenzen.dcm.antlr.dcmParser.Dcm_file_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Displayname_expContext;
import net.alenzen.dcm.antlr.dcmParser.Distribution_expContext;
import net.alenzen.dcm.antlr.dcmParser.Distribution_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Fixed_characteristic_line_expContext;
import net.alenzen.dcm.antlr.dcmParser.Fixed_characteristic_line_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Fixed_characteristic_map_expContext;
import net.alenzen.dcm.antlr.dcmParser.Fixed_characteristic_map_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Function_expContext;
import net.alenzen.dcm.antlr.dcmParser.Functions_expContext;
import net.alenzen.dcm.antlr.dcmParser.Functions_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Group_characteristic_line_expContext;
import net.alenzen.dcm.antlr.dcmParser.Group_characteristic_line_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Group_characteristic_map_expContext;
import net.alenzen.dcm.antlr.dcmParser.Group_characteristic_map_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Longname_expContext;
import net.alenzen.dcm.antlr.dcmParser.Matrix_expContext;
import net.alenzen.dcm.antlr.dcmParser.Matrix_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Module_header_expContext;
import net.alenzen.dcm.antlr.dcmParser.Module_header_short_expContext;
import net.alenzen.dcm.antlr.dcmParser.Name_value_pairContext;
import net.alenzen.dcm.antlr.dcmParser.Number_value_expContext;
import net.alenzen.dcm.antlr.dcmParser.Number_value_list_expContext;
import net.alenzen.dcm.antlr.dcmParser.Parameter_expContext;
import net.alenzen.dcm.antlr.dcmParser.Parameter_sub_expContext;
import net.alenzen.dcm.antlr.dcmParser.Sstx_expContext;
import net.alenzen.dcm.antlr.dcmParser.Ssty_expContext;
import net.alenzen.dcm.antlr.dcmParser.St_x_expContext;
import net.alenzen.dcm.antlr.dcmParser.St_y_expContext;
import net.alenzen.dcm.antlr.dcmParser.String_expContext;
import net.alenzen.dcm.antlr.dcmParser.String_value_expContext;
import net.alenzen.dcm.antlr.dcmParser.String_value_list_expContext;
import net.alenzen.dcm.antlr.dcmParser.Unit_w_expContext;
import net.alenzen.dcm.antlr.dcmParser.Unit_x_expContext;
import net.alenzen.dcm.antlr.dcmParser.Unit_y_expContext;
import net.alenzen.dcm.antlr.dcmParser.ValueContext;
import net.alenzen.dcm.antlr.dcmParser.Value_expContext;
import net.alenzen.dcm.antlr.dcmParser.Value_list_expContext;
import net.alenzen.dcm.antlr.dcmParser.Var_expContext;
import net.alenzen.dcm.antlr.dcmParser.Variant_coding_expContext;
import net.alenzen.dcm.antlr.dcmParser.Variant_coding_sub_expContext;
import net.alenzen.dcm.antlr.dcmParserBaseVisitor;

public class DcmVisitor extends dcmParserBaseVisitor<Object> {
	private IParserEventHandler log;

	public DcmVisitor() {
		log = new DefaultParserEventHandler();
	}

	public DcmVisitor(IParserEventHandler logger) {
		log = logger;

		if (log == null) {
			log = new DefaultParserEventHandler();
		}
	}

	@Override
	public Object visitDcm_file(Dcm_fileContext ctx) {
		DcmFile dcmFile = new DcmFile();

		visitDcmFileSubNodes(dcmFile, ctx.dcm_file_sub_exp());

		return dcmFile;
	}

	private void visitDcmFileSubNodes(DcmFile dcmFile, Dcm_file_sub_expContext ctx) {
		dcmFile.setFunctionGroups(visitMultipleOpt(ctx.functions_exp(), FunctionGroup.class));
		dcmFile.setVariantCodings(visitMultipleOpt(ctx.variant_coding_exp(), VariantCoding.class));
		dcmFile.setModuleHeaders(visitMultipleOpt(ctx.module_header_exp(), ModuleHeader.class));
		dcmFile.setParameters(visitMultipleOpt(ctx.parameter_exp(), Parameter.class));
		dcmFile.setArrays(visitMultipleOpt(ctx.array_exp(), Array.class));
		dcmFile.setMatrices(visitMultipleOpt(ctx.matrix_exp(), Matrix.class));
		dcmFile.setCharacteristicLines(visitMultipleOpt(ctx.characteristic_line_exp(), CharacteristicLine.class));
		dcmFile.setCharacteristicMaps(visitMultipleOpt(ctx.characteristic_map_exp(), CharacteristicMap.class));
		dcmFile.setFixedCharacteristicLines(
				visitMultipleOpt(ctx.fixed_characteristic_line_exp(), FixedCharacteristicLine.class));
		dcmFile.setFixedCharacteristicMaps(
				visitMultipleOpt(ctx.fixed_characteristic_map_exp(), FixedCharacteristicMap.class));
		dcmFile.setGroupCharacteristicLines(
				visitMultipleOpt(ctx.group_characteristic_line_exp(), GroupCharacteristicLine.class));
		dcmFile.setGroupCharacteristicMaps(
				visitMultipleOpt(ctx.group_characteristic_map_exp(), GroupCharacteristicMap.class));
		dcmFile.setDistributions(visitMultipleOpt(ctx.distribution_exp(), Distribution.class));
	}

	@Override
	public Object visitDistribution_exp(Distribution_expContext ctx) {
		Distribution distr = new Distribution();

		distr.setName(ctx.name.getText());
		distr.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Distribution_sub_expContext subExp = ctx.distribution_sub_exp();
		distr.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		distr.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		distr.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		distr.setFunction((String) visitSingleOpt(subExp.function_exp()));
		distr.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		distr.setStx(visitSamplePointXList(subExp.st_x_exp()));

		return distr;
	}

	@Override
	public Object visitGroup_characteristic_map_exp(Group_characteristic_map_expContext ctx) {
		GroupCharacteristicMap m = new GroupCharacteristicMap();

		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Group_characteristic_map_sub_expContext subExp = ctx.group_characteristic_map_sub_exp();
		m.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		m.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		m.setFunction((String) visitSingleOpt(subExp.function_exp()));
		m.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		m.setUnitY((String) visitSingleOpt(subExp.unit_y_exp()));
		m.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		m.setSstx((String) visitSingleOpt(subExp.sstx_exp()));
		m.setSsty((String) visitSingleOpt(subExp.ssty_exp()));
		m.setStx(visitSamplePointXList(subExp.st_x_exp()));
		m.setSty(visitMultipleOpt(subExp.st_y_exp(), IValue.class));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@Override
	public Object visitGroup_characteristic_line_exp(Group_characteristic_line_expContext ctx) {
		GroupCharacteristicLine line = new GroupCharacteristicLine();

		line.setName(ctx.name.getText());
		line.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Group_characteristic_line_sub_expContext subExp = ctx.group_characteristic_line_sub_exp();
		line.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		line.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		line.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		line.setFunction((String) visitSingleOpt(subExp.function_exp()));
		line.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		line.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		line.setSstx((String) visitSingleOpt(subExp.sstx_exp()));
		line.setStx(visitSamplePointXList(subExp.st_x_exp()));
		line.setValues(visitValueList(subExp.value_list_exp()));

		return line;
	}

	@Override
	public Object visitUnit_w_exp(Unit_w_expContext ctx) {
		return visitString(ctx.string_exp());
	}

	@Override
	public Object visitUnit_x_exp(Unit_x_expContext ctx) {
		return visitString(ctx.string_exp());
	}

	@Override
	public Object visitUnit_y_exp(Unit_y_expContext ctx) {
		return visitString(ctx.string_exp());
	}

	@Override
	public Object visitSstx_exp(Sstx_expContext ctx) {
		String t = ctx.SSTX().getText();
		return t.replaceFirst("\\*SSTX", "").trim();
	}

	@Override
	public Object visitSsty_exp(Ssty_expContext ctx) {
		String t = ctx.SSTY().getText();
		return t.replaceFirst("\\*SSTY", "").trim();
	}

	@Override
	public Object visitFixed_characteristic_map_exp(Fixed_characteristic_map_expContext ctx) {
		FixedCharacteristicMap m = new FixedCharacteristicMap();

		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Fixed_characteristic_map_sub_expContext subExp = ctx.fixed_characteristic_map_sub_exp();
		m.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		m.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		m.setFunction((String) visitSingleOpt(subExp.function_exp()));
		m.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		m.setUnitY((String) visitSingleOpt(subExp.unit_y_exp()));
		m.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		m.setStx(visitSamplePointXList(subExp.st_x_exp()));
		m.setSty(visitMultipleOpt(subExp.st_y_exp(), IValue.class));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@Override
	public Object visitFixed_characteristic_line_exp(Fixed_characteristic_line_expContext ctx) {
		FixedCharacteristicLine line = new FixedCharacteristicLine();

		line.setName(ctx.name.getText());
		line.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Fixed_characteristic_line_sub_expContext subExp = ctx.fixed_characteristic_line_sub_exp();
		line.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		line.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		line.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		line.setFunction((String) visitSingleOpt(subExp.function_exp()));
		line.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		line.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		line.setStx(visitSamplePointXList(subExp.st_x_exp()));
		line.setValues(visitValueList(subExp.value_list_exp()));

		return line;
	}

	@Override
	public Object visitCharacteristic_map_exp(Characteristic_map_expContext ctx) {
		CharacteristicMap m = new CharacteristicMap();

		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Characteristic_map_sub_expContext subExp = ctx.characteristic_map_sub_exp();
		m.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		m.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		m.setFunction((String) visitSingleOpt(subExp.function_exp()));
		m.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		m.setUnitY((String) visitSingleOpt(subExp.unit_y_exp()));
		m.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		m.setStx(visitSamplePointXList(subExp.st_x_exp()));
		m.setSty(visitMultipleOpt(subExp.st_y_exp(), IValue.class));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@Override
	public Object visitSt_y_exp(St_y_expContext ctx) {
		return visit(ctx.value());
	}

	@Override
	public Object visitCharacteristic_line_exp(Characteristic_line_expContext ctx) {
		CharacteristicLine line = new CharacteristicLine();

		line.setName(ctx.name.getText());
		line.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Characteristic_line_sub_expContext subExp = ctx.characteristic_line_sub_exp();
		line.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		line.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		line.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		line.setFunction((String) visitSingleOpt(subExp.function_exp()));
		line.setUnitX((String) visitSingleOpt(subExp.unit_x_exp()));
		line.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		line.setStx(visitSamplePointXList(subExp.st_x_exp()));
		line.setValues(visitValueList(subExp.value_list_exp()));

		return line;
	}

	@SuppressWarnings("unchecked")
	private List<IValue> visitSamplePointXList(List<St_x_expContext> ctx) {
		return (List<IValue>) visitSingleOpt(ctx);
	}

	@Override
	public Object visitSt_x_exp(St_x_expContext ctx) {
		return visitMultipleOpt(ctx.value(), IValue.class);
	}

	@Override
	public Object visitMatrix_exp(Matrix_expContext ctx) {
		Matrix m = new Matrix();

		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Matrix_sub_expContext subExp = ctx.matrix_sub_exp();
		m.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		m.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		m.setFunction((String) visitSingleOpt(subExp.function_exp()));
		m.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@SuppressWarnings("unchecked")
	private List<List<IValue>> visitValueMatrix(List<Value_list_expContext> ctx) {
		List<List<IValue>> mv = new ArrayList<List<IValue>>();

		for (Value_list_expContext vCtx : ctx) {
			mv.add((List<IValue>) visit(vCtx));
		}

		return mv;
	}

	@Override
	public Object visitArray_exp(Array_expContext ctx) {
		Array ar = new Array();

		ar.setName(ctx.name.getText());
		ar.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Array_sub_expContext subExp = ctx.array_sub_exp();
		ar.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		ar.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		ar.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		ar.setFunction((String) visitSingleOpt(subExp.function_exp()));
		ar.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		ar.setValues(visitValueList(subExp.value_list_exp()));

		return ar;
	}

	@SuppressWarnings("unchecked")
	private List<IValue> visitValueList(List<Value_list_expContext> ctx) {
		return (List<IValue>) visitSingleOpt(ctx);
	}

	@Override
	public Object visitValue_list_exp(Value_list_expContext ctx) {
		if (ctx.number_value_list_exp() != null) {
			return visit(ctx.number_value_list_exp());
		}

		if (ctx.string_value_list_exp() != null) {
			return visit(ctx.string_value_list_exp());
		}

		return null;
	}

	@Override
	public Object visitNumber_value_list_exp(Number_value_list_expContext ctx) {
		return visitMultipleOpt(ctx.value(), IValue.class);
	}

	@Override
	public Object visitString_value_list_exp(String_value_list_expContext ctx) {
		List<String> values = visitMultipleOpt(ctx.string_exp(), String.class);
		return values.stream().map(s -> ValueFactory.fromString(s)).collect(Collectors.toList());
	}

	@Override
	public Object visitParameter_exp(Parameter_expContext ctx) {
		Parameter par = new Parameter();

		par.setName(ctx.name.getText());

		Parameter_sub_expContext subExp = ctx.parameter_sub_exp();
		par.setLongName((String) visitSingleOpt(subExp.longname_exp()));
		par.setDisplayName((String) visitSingleOpt(subExp.displayname_exp()));
		par.setVariantValues(visitSingleOptVar(subExp.var_exp()));
		par.setFunction((String) visitSingleOpt(subExp.function_exp()));
		par.setUnitW((String) visitSingleOpt(subExp.unit_w_exp()));
		par.setValue((IValue) visitSingleOpt(subExp.value_exp()));

		return par;
	}

	@Override
	public Object visitFunction_exp(Function_expContext ctx) {
		return ctx.name.getText();
	}

	private List<VariantValue> visitSingleOptVar(List<Var_expContext> list) {
		if (list.isEmpty()) {
			return null;
		}

		ArrayList<VariantValue> values = new ArrayList<VariantValue>();

		for (Var_expContext nv : list) {
			values.addAll(visitMultipleOpt(nv.name_value_pair(), VariantValue.class));
		}

		return values;
	}

	@Override
	public Object visitName_value_pair(Name_value_pairContext ctx) {
		VariantValue v = new VariantValue();

		v.setName(ctx.name.getText());
		v.setValue((IValue) visit(ctx.value()));

		return v;
	}

	@Override
	public Object visitLongname_exp(Longname_expContext ctx) {
		return visitString(ctx.string_exp());
	}

	@Override
	public Object visitDisplayname_exp(Displayname_expContext ctx) {
		return ctx.parameter_name_exp().getText();
	}

	@Override
	public Object visitModule_header_exp(Module_header_expContext ctx) {
		ModuleHeader mh = new ModuleHeader();
		mh.setName(ctx.name.getText());

		List<String> text = new ArrayList<String>();
		text.add(visitString(ctx.text));
		text.addAll(visitMultipleOpt(ctx.lines, String.class));
		mh.setText(text);

		return mh;
	}

	@Override
	public Object visitModule_header_short_exp(Module_header_short_expContext ctx) {
		return visitString_exp(ctx.text);
	}

	@Override
	public Object visitVariant_coding_exp(Variant_coding_expContext ctx) {
		VariantCoding vc = new VariantCoding();

		vc.setCriteria(visitMultipleOpt(ctx.variant_coding_sub_exp(), Criterion.class));

		return vc;
	}

	@Override
	public Object visitVariant_coding_sub_exp(Variant_coding_sub_expContext ctx) {
		Criterion crit = new Criterion();

		crit.setName(ctx.name.getText());
		crit.setValues(visitMultipleOpt(ctx.values, IValue.class));

		return crit;
	}

	@Override
	public Object visitNumber_value_exp(Number_value_expContext ctx) {
		return visit(ctx.value());
	}

	@Override
	public Object visitString_value_exp(String_value_expContext ctx) {
		return visit(ctx.string_exp());
	}

	@Override
	public Object visitValue_exp(Value_expContext ctx) {
		if (ctx.number_value_exp() != null) {
			return visit(ctx.number_value_exp().value());
		}

		if (ctx.string_value_exp() != null) {
			return ValueFactory.fromString(visitString(ctx.string_value_exp().string_exp()));
		}

		log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
				"Unknown value type (expected WERT or TEXT): " + ctx.getText());
		return null;
	}

	@Override
	public Object visitValue(ValueContext ctx) {
		if (ctx.TRUE() != null) {
			return ValueFactory.fromBoolean(true);
		} else if (ctx.FALSE() != null) {
			return ValueFactory.fromBoolean(false);
		}

		return ValueFactory.fromNumber(ctx.getText());
	}

	@Override
	public Object visitFunctions_exp(Functions_expContext ctx) {
		FunctionGroup functionGroup = new FunctionGroup();
		functionGroup.setFunctions(visitMultipleOpt(ctx.functions_sub_exp(), Function.class));
		return functionGroup;
	}

	@Override
	public Object visitFunctions_sub_exp(Functions_sub_expContext ctx) {
		Function function = new Function();

		function.setName(ctx.name.getText());
		function.setVersion(visitString(ctx.version));
		function.setLongname(visitString(ctx.longname));

		return function;
	}

	@Override
	public Object visitString_exp(String_expContext ctx) {
		return extractString(ctx.string);
	}

	/*
	 * HELPER FUNCTIONS copied from net.alenzen.a2l.A2LVisitor
	 */

	private Object visitOpt(ParseTree tree) {
		if (tree == null) {
			return null;
		}

		return visit(tree);
	}

	private <T, U extends ParserRuleContext> List<T> visitMultipleOpt(List<U> rules, Class<T> c) {
		List<T> output = new ArrayList<T>();
		for (U ctx : rules) {
			Object o = visitOpt(ctx);
			if (o != null) {
				if (c.isInstance(o)) {
					output.add(c.cast(o));
				} else {
					log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
							"Invalid sub type: " + ctx.getText() + " (expected " + c.getName() + ")");
				}
			} else {
				log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), "Cannot parse sub node!");
			}
		}
		return output;
	}

	private <T extends ParserRuleContext> Object visitSingleOpt(List<T> rules) {
		if (rules.size() > 1) {
			log.log(rules.get(0).getStart().getLine(), rules.get(0).getStart().getCharPositionInLine(),
					"More than one element of the keyword is specified!");
		}

		if (rules.size() <= 0) {
			return null;
		}

		return visitOpt(rules.get(0));
	}

	private String visitString(ParserRuleContext t) {
		if (t instanceof String_expContext)
			return (String) visit(t);

		log.log(t.getStart().getLine(), t.getStart().getCharPositionInLine(), "Exptected a string: " + t.getText());
		return null;
	}

	private String extractString(Token stringToken) {
		String stringVal = stringToken.getText();

		if (stringToken.getType() != dcmLexer.STRING) {
			log.log(stringToken.getLine(), stringToken.getCharPositionInLine(),
					"Exptected a string: " + stringToken.getText());
			return stringVal;
		}

		try {
			return toJavaString(stringVal);
		} catch (Exception e) {
			log.log(stringToken.getLine(), stringToken.getCharPositionInLine(),
					"Cannot match string: " + stringToken.getText());
		}

		return stringVal;
	}

	public static String toJavaString(String a2lString) throws InvalidParameterException {
		if (a2lString.startsWith("\"") && a2lString.endsWith("\"")) {
			// remove trailing "
			a2lString = a2lString.substring(1, a2lString.length() - 1);

			return replaceEscapedCharacters(a2lString);
		}

		throw new InvalidParameterException("The given string is not enclosed by \": " + a2lString);
	}

	private static String replaceEscapedCharacters(String string) {
		String[] splittedDoubleBackslash = string.split("\\\\\\\\", -1);

		for (int i = 0; i < splittedDoubleBackslash.length; i++) {
			String stringVal = splittedDoubleBackslash[i];
			// replace escaped "
			stringVal = stringVal.replaceAll("\\\\\\\"|\"\"", "\"");

			// replace escaped '
			stringVal = stringVal.replaceAll("\\\\'", "'");

			// replace \n
			stringVal = stringVal.replaceAll("\\\\n", "\n");

			// replace \r
			stringVal = stringVal.replaceAll("\\\\r", "\r");

			// replace \t
			stringVal = stringVal.replaceAll("\\\\t", "\t");

			splittedDoubleBackslash[i] = stringVal;
		}

		return String.join("\\", splittedDoubleBackslash);
	}
}
