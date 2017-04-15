package io.github.rockerhieu.customlint;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiReferenceExpression;
import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static com.android.tools.lint.client.api.JavaParser.TYPE_STRING;

public class TrackerDetector extends Detector implements Detector.JavaPsiScanner {
  private static final Class<? extends Detector> DETECTOR_CLASS = TrackerDetector.class;
  private static final EnumSet<Scope> DETECTOR_SCOPE = Scope.JAVA_FILE_SCOPE;

  private static final Implementation IMPLEMENTATION =
      new Implementation(DETECTOR_CLASS, DETECTOR_SCOPE);

  public static final Issue INVALID_TRACKER_ACTION_ISSUE =
      Issue.create("InvalidTrackerAction", "Event action must follow some naming conventions",
          "Event action must be between 2 and 40 characters long and must consist of alphanumberic characters, _, -, or spaces",
          Category.CORRECTNESS, 6, Severity.WARNING, IMPLEMENTATION);

  private static final String TRACKER_CLASS =
      "io.github.rockerhieu.customlintsdemo.analytics.SendEventBuilder";

  @Override public boolean appliesTo(@NonNull Context context, @NonNull File file) {
    return true;
  }

  @Override public List<String> getApplicableMethodNames() {
    return Arrays.asList("action");
  }

  @Override public void visitMethod(JavaContext context, JavaElementVisitor visitor,
      PsiMethodCallExpression call, PsiMethod method) {
    if (method == null || method.getContainingClass() == null || !TRACKER_CLASS.equals(
        method.getContainingClass().getQualifiedName())) {
      return;
    }
    JavaEvaluator evaluator = context.getEvaluator();
    if (!evaluator.isMemberInClass(method, TRACKER_CLASS)) {
      return;
    }
    if (!evaluator.methodMatches(method, TRACKER_CLASS, true, TYPE_STRING)) {
      return;
    }
    String name = method.getName();
    if ("action".equals(name)) {
      checkAction(context, call, method);
      return;
    }
  }

  private void checkAction(JavaContext context, PsiMethodCallExpression call, PsiMethod method) {
    if (context.isEnabled(INVALID_TRACKER_ACTION_ISSUE)) {
      PsiExpression actionExpression = call.getArgumentList().getExpressions()[0];
      String action = findLiteralValue(actionExpression);
      if (action == null || !action.matches("[a-zA-Z_\\- ]{2,40}")) {
        context.report(INVALID_TRACKER_ACTION_ISSUE, call, context.getLocation(actionExpression),
            "The action name must be between 2 and 40 characters long and must consist of alphanumberic characters, _, -, and spaces");
      }
    }
  }

  private static String findLiteralValue(PsiExpression argument) {
    if (argument instanceof PsiLiteralExpression) {
      PsiLiteralExpression literalExpression = (PsiLiteralExpression) argument;
      Object value = literalExpression.getValue();
      if (value instanceof String) {
        return (String) value;
      }
    } else if (argument instanceof PsiBinaryExpression) {
      PsiBinaryExpression binaryExpression = (PsiBinaryExpression) argument;
      if (binaryExpression.getOperationTokenType() == JavaTokenType.PLUS) {
        String left = findLiteralValue(binaryExpression.getLOperand());
        String right = findLiteralValue(binaryExpression.getROperand());
        if (left != null && right != null) {
          return left + right;
        }
      }
    } else if (argument instanceof PsiReferenceExpression) {
      PsiReferenceExpression referenceExpression = (PsiReferenceExpression) argument;
      PsiElement resolved = referenceExpression.resolve();
      if (resolved instanceof PsiField) {
        PsiField field = (PsiField) resolved;
        Object value = field.computeConstantValue();
        if (value instanceof String) {
          return (String) value;
        }
      }
    }

    return null;
  }
}