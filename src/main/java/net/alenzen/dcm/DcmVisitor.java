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
import net.alenzen.dcm.antlr.dcmParser.Comment_expContext;
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

		dcmFile.setComments(visitComments(ctx.comment_exp()));
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

		distr.setComments(visitComments(ctx.preComment));
		distr.setEndComments(visitComments(ctx.endComment));
		distr.setName(ctx.name.getText());
		distr.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Distribution_sub_expContext subExp = ctx.distribution_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		distr.setLongNameComments(cs.comments);
		distr.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		distr.setDisplayNameComments(cs.comments);
		distr.setDisplayName(cs.value);

		distr.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		distr.setFunctionComments(cs.comments);
		distr.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		distr.setUnitXComments(cs.comments);
		distr.setUnitX(cs.value);

		distr.setStx(visitSamplePointXList(subExp.st_x_exp()));

		return distr;
	}

	@Override
	public Object visitGroup_characteristic_map_exp(Group_characteristic_map_expContext ctx) {
		GroupCharacteristicMap m = new GroupCharacteristicMap();

		m.setComments(visitComments(ctx.preComment));
		m.setEndComments(visitComments(ctx.endComment));
		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Group_characteristic_map_sub_expContext subExp = ctx.group_characteristic_map_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		m.setLongNameComments(cs.comments);
		m.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		m.setDisplayNameComments(cs.comments);
		m.setDisplayName(cs.value);

		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		m.setFunctionComments(cs.comments);
		m.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		m.setUnitWComments(cs.comments);
		m.setUnitW(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		m.setUnitXComments(cs.comments);
		m.setUnitX(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_y_exp());
		m.setUnitYComments(cs.comments);
		m.setUnitY(cs.value);

		m.setStx(visitSamplePointXList(subExp.st_x_exp()));
		m.setSty(visitMultipleOpt(subExp.st_y_exp(), Value.class));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		cs = visitSingleCommentedStringOpt(subExp.sstx_exp());
		m.setSstxComments(cs.comments);
		m.setSstx(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.ssty_exp());
		m.setSstyComments(cs.comments);
		m.setSsty(cs.value);

		return m;
	}

	@Override
	public Object visitGroup_characteristic_line_exp(Group_characteristic_line_expContext ctx) {
		GroupCharacteristicLine line = new GroupCharacteristicLine();

		line.setComments(visitComments(ctx.preComment));
		line.setEndComments(visitComments(ctx.endComment));
		line.setName(ctx.name.getText());
		line.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Group_characteristic_line_sub_expContext subExp = ctx.group_characteristic_line_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		line.setLongNameComments(cs.comments);
		line.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		line.setDisplayNameComments(cs.comments);
		line.setDisplayName(cs.value);

		line.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		line.setFunctionComments(cs.comments);
		line.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		line.setUnitWComments(cs.comments);
		line.setUnitW(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		line.setUnitXComments(cs.comments);
		line.setUnitX(cs.value);

		line.setStx(visitSamplePointXList(subExp.st_x_exp()));
		line.setValues(visitValueList(subExp.value_list_exp()));

		cs = visitSingleCommentedStringOpt(subExp.sstx_exp());
		line.setSstxComments(cs.comments);
		line.setSstx(cs.value);

		return line;
	}

	@Override
	public Object visitUnit_w_exp(Unit_w_expContext ctx) {
		CommentedString cs = new CommentedString();
		cs.comments = visitComments(ctx.comment_exp());
		cs.value = visitString(ctx.string_exp());
		return cs;
	}

	@Override
	public Object visitUnit_x_exp(Unit_x_expContext ctx) {
		CommentedString cs = new CommentedString();
		cs.comments = visitComments(ctx.comment_exp());
		cs.value = visitString(ctx.string_exp());
		return cs;
	}

	@Override
	public Object visitUnit_y_exp(Unit_y_expContext ctx) {
		CommentedString cs = new CommentedString();
		cs.comments = visitComments(ctx.comment_exp());
		cs.value = visitString(ctx.string_exp());
		return cs;
	}

	@Override
	public Object visitSstx_exp(Sstx_expContext ctx) {
		String t = ctx.SSTX().getText();
		CommentedString cs = new CommentedString();
		cs.value = t.replaceFirst("\\*SSTX", "").trim();
		cs.comments = visitComments(ctx.comment_exp());
		return cs;
	}

	@Override
	public Object visitSsty_exp(Ssty_expContext ctx) {
		String t = ctx.SSTY().getText();
		CommentedString cs = new CommentedString();
		cs.value = t.replaceFirst("\\*SSTY", "").trim();
		cs.comments = visitComments(ctx.comment_exp());
		return cs;
	}

	@Override
	public Object visitFixed_characteristic_map_exp(Fixed_characteristic_map_expContext ctx) {
		FixedCharacteristicMap m = new FixedCharacteristicMap();

		m.setComments(visitComments(ctx.preComment));
		m.setEndComments(visitComments(ctx.endComment));
		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Fixed_characteristic_map_sub_expContext subExp = ctx.fixed_characteristic_map_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		m.setLongNameComments(cs.comments);
		m.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		m.setDisplayNameComments(cs.comments);
		m.setDisplayName(cs.value);

		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		m.setFunctionComments(cs.comments);
		m.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		m.setUnitWComments(cs.comments);
		m.setUnitW(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		m.setUnitXComments(cs.comments);
		m.setUnitX(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_y_exp());
		m.setUnitYComments(cs.comments);
		m.setUnitY(cs.value);

		m.setStx(visitSamplePointXList(subExp.st_x_exp()));
		m.setSty(visitMultipleOpt(subExp.st_y_exp(), Value.class));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@Override
	public Object visitFixed_characteristic_line_exp(Fixed_characteristic_line_expContext ctx) {
		FixedCharacteristicLine line = new FixedCharacteristicLine();

		line.setComments(visitComments(ctx.preComment));
		line.setEndComments(visitComments(ctx.endComment));
		line.setName(ctx.name.getText());
		line.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Fixed_characteristic_line_sub_expContext subExp = ctx.fixed_characteristic_line_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		line.setLongNameComments(cs.comments);
		line.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		line.setDisplayNameComments(cs.comments);
		line.setDisplayName(cs.value);

		line.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		line.setFunctionComments(cs.comments);
		line.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		line.setUnitWComments(cs.comments);
		line.setUnitW(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		line.setUnitXComments(cs.comments);
		line.setUnitX(cs.value);

		line.setStx(visitSamplePointXList(subExp.st_x_exp()));
		line.setValues(visitValueList(subExp.value_list_exp()));

		return line;
	}

	@Override
	public Object visitCharacteristic_map_exp(Characteristic_map_expContext ctx) {
		CharacteristicMap m = new CharacteristicMap();

		m.setComments(visitComments(ctx.preComment));
		m.setEndComments(visitComments(ctx.endComment));
		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Characteristic_map_sub_expContext subExp = ctx.characteristic_map_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		m.setLongNameComments(cs.comments);
		m.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		m.setDisplayNameComments(cs.comments);
		m.setDisplayName(cs.value);

		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		m.setFunctionComments(cs.comments);
		m.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		m.setUnitWComments(cs.comments);
		m.setUnitW(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		m.setUnitXComments(cs.comments);
		m.setUnitX(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_y_exp());
		m.setUnitYComments(cs.comments);
		m.setUnitY(cs.value);

		m.setStx(visitSamplePointXList(subExp.st_x_exp()));
		m.setSty(visitMultipleOpt(subExp.st_y_exp(), Value.class));
		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@Override
	public Object visitSt_y_exp(St_y_expContext ctx) {
		Value v = (Value) visit(ctx.value());
		v.setComments(visitComments(ctx.comment_exp()));
		return v;
	}

	@Override
	public Object visitCharacteristic_line_exp(Characteristic_line_expContext ctx) {
		CharacteristicLine line = new CharacteristicLine();

		line.setComments(visitComments(ctx.preComment));
		line.setEndComments(visitComments(ctx.endComment));
		line.setName(ctx.name.getText());
		line.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Characteristic_line_sub_expContext subExp = ctx.characteristic_line_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		line.setLongNameComments(cs.comments);
		line.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		line.setDisplayNameComments(cs.comments);
		line.setDisplayName(cs.value);

		line.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		line.setFunctionComments(cs.comments);
		line.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		line.setUnitWComments(cs.comments);
		line.setUnitW(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_x_exp());
		line.setUnitXComments(cs.comments);
		line.setUnitX(cs.value);

		line.setStx(visitSamplePointXList(subExp.st_x_exp()));
		line.setValues(visitValueList(subExp.value_list_exp()));

		return line;
	}

	@SuppressWarnings("unchecked")
	private List<Value> visitSamplePointXList(List<St_x_expContext> ctx) {
		return (List<Value>) visitSingleOpt(ctx);
	}

	@Override
	public Object visitSt_x_exp(St_x_expContext ctx) {
		List<Value> vl = visitMultipleOpt(ctx.value(), Value.class);
		if (!vl.isEmpty()) {
			vl.get(0).setComments(visitComments(ctx.comment_exp()));
		}
		return vl;
	}

	@Override
	public Object visitMatrix_exp(Matrix_expContext ctx) {
		Matrix m = new Matrix();

		m.setComments(visitComments(ctx.preComment));
		m.setEndComments(visitComments(ctx.endComment));
		m.setName(ctx.name.getText());
		m.setSizeX(Integer.parseInt(ctx.sizeX.getText()));
		m.setSizeY(Integer.parseInt(ctx.sizeY.getText()));

		Matrix_sub_expContext subExp = ctx.matrix_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		m.setLongNameComments(cs.comments);
		m.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		m.setDisplayNameComments(cs.comments);
		m.setDisplayName(cs.value);

		m.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		m.setFunctionComments(cs.comments);
		m.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		m.setUnitWComments(cs.comments);
		m.setUnitW(cs.value);

		m.setValues(visitValueMatrix(subExp.value_list_exp()));

		return m;
	}

	@SuppressWarnings("unchecked")
	private List<List<Value>> visitValueMatrix(List<Value_list_expContext> ctx) {
		List<List<Value>> mv = new ArrayList<List<Value>>();

		for (Value_list_expContext vCtx : ctx) {
			mv.add((List<Value>) visit(vCtx));
		}

		return mv;
	}

	@Override
	public Object visitArray_exp(Array_expContext ctx) {
		Array ar = new Array();

		ar.setComments(visitComments(ctx.preComment));
		ar.setEndComments(visitComments(ctx.endComment));
		ar.setName(ctx.name.getText());
		ar.setSizeX(Integer.parseInt(ctx.sizeX.getText()));

		Array_sub_expContext subExp = ctx.array_sub_exp();
		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		ar.setLongNameComments(cs.comments);
		ar.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		ar.setDisplayNameComments(cs.comments);
		ar.setDisplayName(cs.value);

		ar.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		ar.setFunctionComments(cs.comments);
		ar.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		ar.setUnitWComments(cs.comments);
		ar.setUnitW(cs.value);

		ar.setValues(visitValueList(subExp.value_list_exp()));

		return ar;
	}

	@SuppressWarnings("unchecked")
	private List<Value> visitValueList(List<Value_list_expContext> ctx) {
		return (List<Value>) visitSingleOpt(ctx);
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
		List<Value> vl = visitMultipleOpt(ctx.value(), Value.class);
		if (!vl.isEmpty()) {
			vl.get(0).setComments(visitComments(ctx.comment_exp()));
		}
		return vl;
	}

	@Override
	public Object visitString_value_list_exp(String_value_list_expContext ctx) {
		List<String> values = visitMultipleOpt(ctx.string_exp(), String.class);
		List<TextValue> vl = values.stream().map(s -> new TextValue(s)).collect(Collectors.toList());
		if (!vl.isEmpty()) {
			vl.get(0).setComments(visitComments(ctx.comment_exp()));
		}
		return vl;
	}

	@Override
	public Object visitParameter_exp(Parameter_expContext ctx) {
		Parameter par = new Parameter();

		par.setComments(visitComments(ctx.preComment));
		par.setName(ctx.name.getText());
		par.setEndComments(visitComments(ctx.endComment));

		Parameter_sub_expContext subExp = ctx.parameter_sub_exp();

		CommentedString cs = visitSingleCommentedStringOpt(subExp.longname_exp());
		par.setLongNameComments(cs.comments);
		par.setLongName(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.displayname_exp());
		par.setDisplayNameComments(cs.comments);
		par.setDisplayName(cs.value);

		par.setVariantValues(visitSingleOptVar(subExp.var_exp()));

		cs = visitSingleCommentedStringOpt(subExp.function_exp());
		par.setFunctionComments(cs.comments);
		par.setFunction(cs.value);

		cs = visitSingleCommentedStringOpt(subExp.unit_w_exp());
		par.setUnitWComments(cs.comments);
		par.setUnitW(cs.value);

		par.setValue((Value) visitSingleOpt(subExp.value_exp()));

		return par;
	}

	private <T extends ParserRuleContext> CommentedString visitSingleCommentedStringOpt(List<T> exp) {
		CommentedString cs = (CommentedString) visitSingleOpt(exp);
		if (cs == null) {
			cs = new CommentedString();
		}
		return cs;
	}

	class CommentedString {
		public List<String> comments;
		public String value;
	}

	@Override
	public Object visitFunction_exp(Function_expContext ctx) {
		CommentedString cs = new CommentedString();
		cs.comments = visitComments(ctx.comment_exp());
		cs.value = ctx.name.getText();
		return cs;
	}

	private List<VariantValue> visitSingleOptVar(List<Var_expContext> list) {
		if (list.isEmpty()) {
			return null;
		}

		ArrayList<VariantValue> values = new ArrayList<VariantValue>();

		for (Var_expContext nv : list) {
			List<? extends VariantValue> mv = visitMultipleOpt(nv.name_value_pair(), VariantValue.class);
			if (!mv.isEmpty()) {
				mv.get(0).setComments(visitComments(nv.comment_exp()));
			}
			values.addAll(mv);
		}

		return values;
	}

	@Override
	public Object visitName_value_pair(Name_value_pairContext ctx) {
		VariantValue v = new VariantValue();

		v.setName(ctx.name.getText());
		v.setValue((Value) visit(ctx.value()));

		return v;
	}

	@Override
	public Object visitLongname_exp(Longname_expContext ctx) {
		CommentedString cs = new CommentedString();
		cs.comments = visitComments(ctx.comment_exp());
		cs.value = visitString(ctx.string_exp());
		return cs;
	}

	@Override
	public Object visitDisplayname_exp(Displayname_expContext ctx) {
		CommentedString cs = new CommentedString();
		cs.comments = visitComments(ctx.comment_exp());
		cs.value = ctx.parameter_name_exp().getText();
		return cs;
	}

	@Override
	public Object visitModule_header_exp(Module_header_expContext ctx) {
		ModuleHeader mh = new ModuleHeader();

		mh.setComments(visitComments(ctx.comment_exp()));
		mh.setName(ctx.name.getText());
		mh.setText(visitString(ctx.text));
		mh.setLines(visitMultipleOpt(ctx.lines, ModuleHeaderLine.class));

		return mh;
	}

	@Override
	public Object visitModule_header_short_exp(Module_header_short_expContext ctx) {
		ModuleHeaderLine l = new ModuleHeaderLine();
		l.setComments(visitComments(ctx.comment_exp()));
		l.setText((String) visitString_exp(ctx.text));
		return l;
	}

	@Override
	public Object visitVariant_coding_exp(Variant_coding_expContext ctx) {
		VariantCoding vc = new VariantCoding();

		vc.setComments(visitComments(ctx.preComment));
		vc.setCriteria(visitMultipleOpt(ctx.variant_coding_sub_exp(), Criterion.class));
		vc.setEndComments(visitComments(ctx.endComment));

		return vc;
	}

	@Override
	public Object visitVariant_coding_sub_exp(Variant_coding_sub_expContext ctx) {
		Criterion crit = new Criterion();

		crit.setComments(visitComments(ctx.comment_exp()));
		crit.setName(ctx.name.getText());
		crit.setValues(visitMultipleOpt(ctx.values, Value.class));

		return crit;
	}

	@Override
	public Object visitNumber_value_exp(Number_value_expContext ctx) {
		return visit(ctx.value());
	}

	@Override
	public Object visitValue_exp(Value_expContext ctx) {
		if (ctx.number_value_exp() != null) {
			Value v = (Value) visit(ctx.number_value_exp().value());
			v.setComments(visitComments(ctx.number_value_exp().comment_exp()));
			return v;
		}

		if (ctx.string_value_exp() != null) {
			TextValue tv = new TextValue(visitString(ctx.string_value_exp().string_exp()));
			tv.setComments(visitComments(ctx.string_value_exp().comment_exp()));
			return tv;
		}

		log.log(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(),
				"Unknown value type (expected WERT or TEXT): " + ctx.getText());
		return null;
	}

	@Override
	public Object visitValue(ValueContext ctx) {
		if (ctx.TRUE() != null) {
			return new BooleanValue(true);
		} else if (ctx.FALSE() != null) {
			return new BooleanValue(false);
		}

		return new NumberValue(ctx.getText());
	}

	@Override
	public Object visitFunctions_exp(Functions_expContext ctx) {
		FunctionGroup functionGroup = new FunctionGroup();
		functionGroup.setComments(visitComments(ctx.preComment));
		functionGroup.setFunctions(visitMultipleOpt(ctx.functions_sub_exp(), Function.class));
		functionGroup.setEndComments(visitComments(ctx.endComment));
		return functionGroup;
	}

	private List<String> visitComments(Comment_expContext comment_exp) {
		if (comment_exp == null) {
			return null;
		}

		return comment_exp.COMMENT().stream().map(c -> c.getText().trim()).collect(Collectors.toList());
	}

	@Override
	public Object visitFunctions_sub_exp(Functions_sub_expContext ctx) {
		Function function = new Function();

		function.setComments(visitComments(ctx.comment_exp()));
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

		if (output.isEmpty()) {
			return null;
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
