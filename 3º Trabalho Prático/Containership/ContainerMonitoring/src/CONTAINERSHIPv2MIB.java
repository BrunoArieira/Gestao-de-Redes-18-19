 
//--AgentGen BEGIN=_BEGIN
//--AgentGen END

import org.snmp4j.smi.*;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.agent.*;
import org.snmp4j.agent.mo.*;
import org.snmp4j.agent.mo.snmp.*;
import org.snmp4j.agent.mo.snmp.smi.*;
import org.snmp4j.agent.request.*;
import org.snmp4j.log.LogFactory;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.agent.mo.snmp.tc.*;


//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class CONTAINERSHIPv2MIB
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static final LogAdapter LOGGER = 
      LogFactory.getLogger(CONTAINERSHIPv2MIB.class);

//--AgentGen BEGIN=_STATIC
//--AgentGen END

  // Factory
  private MOFactory moFactory = 
    DefaultMOFactory.getInstance();

  // Constants 
  public static final OID oidContImageIndex = 
    new OID(new int[] { 1,3,6,1,3,2019,1,1,0 });
  public static final OID oidContName = 
    new OID(new int[] { 1,3,6,1,3,2019,1,2,0 });
  public static final OID oidContCreateFlag = 
    new OID(new int[] { 1,3,6,1,3,2019,1,3,0 });
  public static final OID oidContainershipUpTime = 
    new OID(new int[] { 1,3,6,1,3,2019,4,1,0 });


  // Enumerations




  // TextualConventions
  private static final String TC_MODULE_CONTAINERSHIPV2_MIB = "CONTAINERSHIPv2-MIB";
  private static final String TC_MODULE_SNMPV2_TC = "SNMPv2-TC";
  private static final String TC_CREATEFLAG = "CreateFlag";
  private static final String TC_DISPLAYSTRING = "DisplayString";

  // Scalars
  private MOScalar contImageIndex;
  private MOScalar contName;
  private MOScalar contCreateFlag;
  private MOScalar containershipUpTime;

  // Tables
  public static final OID oidContImageTableEntry = 
    new OID(new int[] { 1,3,6,1,3,2019,2,1,1 });

  // Index OID definitions
  public static final OID oidImageId =
    new OID(new int[] { 1,3,6,1,3,2019,2,1,1,1 });

  // Column TC definitions for contImageTableEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
    
  // Column sub-identifer definitions for contImageTableEntry:
  public static final int colImageId = 1;
  public static final int colImageName = 2;
  public static final int colImageVers = 3;

  // Column index definitions for contImageTableEntry:
  public static final int idxImageId = 0;
  public static final int idxImageName = 1;
  public static final int idxImageVers = 2;

  private MOTableSubIndex[] contImageTableEntryIndexes;
  private MOTableIndex contImageTableEntryIndex;
  
  private MOTable      contImageTableEntry;
  private MOTableModel contImageTableEntryModel;
  public static final OID oidContainerTableEntry = 
    new OID(new int[] { 1,3,6,1,3,2019,3,1,1 });

  // Index OID definitions
  public static final OID oidContainerId =
    new OID(new int[] { 1,3,6,1,3,2019,3,1,1,1 });

  // Column TC definitions for containerTableEntry:
    
  // Column sub-identifer definitions for containerTableEntry:
  public static final int colContainerId = 1;
  public static final int colContainerName = 2;
  public static final int colContainerImgId = 3;
  public static final int colContainerStatus = 4;
  public static final int colContainerProcUse = 5;

  // Column index definitions for containerTableEntry:
  public static final int idxContainerId = 0;
  public static final int idxContainerName = 1;
  public static final int idxContainerImgId = 2;
  public static final int idxContainerStatus = 3;
  public static final int idxContainerProcUse = 4;

  private MOTableSubIndex[] containerTableEntryIndexes;
  private MOTableIndex containerTableEntryIndex;
  
  private MOTable      containerTableEntry;
  private MOTableModel containerTableEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a CONTAINERSHIPv2-MIB instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected CONTAINERSHIPv2MIB() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a CONTAINERSHIPv2-MIB instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public CONTAINERSHIPv2MIB(MOFactory moFactory) {
    createMO(moFactory);
//--AgentGen BEGIN=_FACTORYCONSTRUCTOR
//--AgentGen END
  }

//--AgentGen BEGIN=_CONSTRUCTORS
//--AgentGen END

  /**
   * Create the ManagedObjects defined for this MIB module
   * using the specified {@link MOFactory}.
   * @param moFactory
   *    the <code>MOFactory</code> instance to use for object 
   *    creation.
   */
  protected void createMO(MOFactory moFactory) {
    addTCsToFactory(moFactory);
    contImageIndex = 
      new ContImageIndex(oidContImageIndex, 
                         moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE));
    contImageIndex.addMOValueValidationListener(new ContImageIndexValidator());
    contName = 
      new ContName(oidContName, 
                   moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE));
    contName.addMOValueValidationListener(new ContNameValidator());
    contCreateFlag = 
      new ContCreateFlag(oidContCreateFlag, 
                         moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE));
    contCreateFlag.addMOValueValidationListener(new ContCreateFlagValidator());
    containershipUpTime = 
      moFactory.createScalar(oidContainershipUpTime,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY), 
                             null,
                             TC_MODULE_SNMPV2_TC, TC_DISPLAYSTRING);
    createContImageTableEntry(moFactory);
    createContainerTableEntry(moFactory);
  }

  public MOScalar getContImageIndex() {
    return contImageIndex;
  }
  public MOScalar getContName() {
    return contName;
  }
  public MOScalar getContCreateFlag() {
    return contCreateFlag;
  }
  public MOScalar getContainershipUpTime() {
    return containershipUpTime;
  }


  public MOTable getContImageTableEntry() {
    return contImageTableEntry;
  }


  private void createContImageTableEntry(MOFactory moFactory) {
    // Index definition
    contImageTableEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidImageId, 
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

    contImageTableEntryIndex = 
      moFactory.createIndex(contImageTableEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=contImageTableEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] contImageTableEntryColumns = new MOColumn[3];
    contImageTableEntryColumns[idxImageId] = 
      new MOMutableColumn(colImageId,
                          SMIConstants.SYNTAX_INTEGER32,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (Integer32)null);
    ValueConstraint imageIdVC = new ConstraintsImpl();
    ((ConstraintsImpl)imageIdVC).add(new Constraint(1L, 1000L));
    ((MOMutableColumn)contImageTableEntryColumns[idxImageId]).
      addMOValueValidationListener(new ValueConstraintValidator(imageIdVC));                                  
    ((MOMutableColumn)contImageTableEntryColumns[idxImageId]).
      addMOValueValidationListener(new ImageIdValidator());
    contImageTableEntryColumns[idxImageName] = 
      new DisplayString(colImageName,
                        moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                        (OctetString)null);
    ValueConstraint imageNameVC = new ConstraintsImpl();
    ((ConstraintsImpl)imageNameVC).add(new Constraint(0L, 255L));
    ((MOMutableColumn)contImageTableEntryColumns[idxImageName]).
      addMOValueValidationListener(new ValueConstraintValidator(imageNameVC));                                  
    ((MOMutableColumn)contImageTableEntryColumns[idxImageName]).
      addMOValueValidationListener(new ImageNameValidator());
    contImageTableEntryColumns[idxImageVers] = 
      new DisplayString(colImageVers,
                        moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                        (OctetString)null);
    ValueConstraint imageVersVC = new ConstraintsImpl();
    ((ConstraintsImpl)imageVersVC).add(new Constraint(0L, 255L));
    ((MOMutableColumn)contImageTableEntryColumns[idxImageVers]).
      addMOValueValidationListener(new ValueConstraintValidator(imageVersVC));                                  
    ((MOMutableColumn)contImageTableEntryColumns[idxImageVers]).
      addMOValueValidationListener(new ImageVersValidator());
    // Table model
    contImageTableEntryModel = 
      moFactory.createTableModel(oidContImageTableEntry,
                                 contImageTableEntryIndex,
                                 contImageTableEntryColumns);
    ((MOMutableTableModel)contImageTableEntryModel).setRowFactory(
      new ContImageTableEntryRowFactory());
    contImageTableEntry = 
      moFactory.createTable(oidContImageTableEntry,
                            contImageTableEntryIndex,
                            contImageTableEntryColumns,
                            contImageTableEntryModel);
  }

  public MOTable getContainerTableEntry() {
    return containerTableEntry;
  }


  private void createContainerTableEntry(MOFactory moFactory) {
    // Index definition
    containerTableEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidContainerId, 
                               SMIConstants.SYNTAX_INTEGER, 1, 1)    };

    containerTableEntryIndex = 
      moFactory.createIndex(containerTableEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=containerTableEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] containerTableEntryColumns = new MOColumn[5];
    containerTableEntryColumns[idxContainerId] = 
      moFactory.createColumn(colContainerId, 
                             SMIConstants.SYNTAX_INTEGER32,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    containerTableEntryColumns[idxContainerName] = 
      moFactory.createColumn(colContainerName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    containerTableEntryColumns[idxContainerImgId] = 
      moFactory.createColumn(colContainerImgId, 
                             SMIConstants.SYNTAX_INTEGER32,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    containerTableEntryColumns[idxContainerStatus] = 
      moFactory.createColumn(colContainerStatus, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    containerTableEntryColumns[idxContainerProcUse] = 
      moFactory.createColumn(colContainerProcUse, 
                             SMIConstants.SYNTAX_INTEGER32,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY));
    // Table model
    containerTableEntryModel = 
      moFactory.createTableModel(oidContainerTableEntry,
                                 containerTableEntryIndex,
                                 containerTableEntryColumns);
    ((MOMutableTableModel)containerTableEntryModel).setRowFactory(
      new ContainerTableEntryRowFactory());
    containerTableEntry = 
      moFactory.createTable(oidContainerTableEntry,
                            containerTableEntryIndex,
                            containerTableEntryColumns,
                            containerTableEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.contImageIndex, context);
    server.register(this.contName, context);
    server.register(this.contCreateFlag, context);
    server.register(this.containershipUpTime, context);
    server.register(this.contImageTableEntry, context);
    server.register(this.containerTableEntry, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.contImageIndex, context);
    server.unregister(this.contName, context);
    server.unregister(this.contCreateFlag, context);
    server.unregister(this.containershipUpTime, context);
    server.unregister(this.contImageTableEntry, context);
    server.unregister(this.containerTableEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars
  public class ContImageIndex extends MOScalar {
    ContImageIndex(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=contImageIndex
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 1L) && (v <= 1000L)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_VALUE;
      }    
     //--AgentGen BEGIN=contImageIndex::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=contImageIndex::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=contImageIndex::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=contImageIndex::_METHODS
     //--AgentGen END

  }

  public class ContName extends DisplayStringScalar {
    ContName(OID oid, MOAccess access) {
      super(oid, access, new OctetString(),
            0, 
            255);
//--AgentGen BEGIN=contName
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_LENGTH;
      }
     //--AgentGen BEGIN=contName::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=contName::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=contName::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=contName::_METHODS
     //--AgentGen END

  }

  public class ContCreateFlag extends MOScalar {
    ContCreateFlag(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=contCreateFlag
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=contCreateFlag::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=contCreateFlag::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=contCreateFlag::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=contCreateFlag::_METHODS
     //--AgentGen END

  }


  // Value Validators
  /**
   * The <code>ContImageIndexValidator</code> implements the value
   * validation for <code>ContImageIndex</code>.
   */
  static class ContImageIndexValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 1L) && (v <= 1000L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=contImageIndex::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>ContNameValidator</code> implements the value
   * validation for <code>ContName</code>.
   */
  static class ContNameValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=contName::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>ContCreateFlagValidator</code> implements the value
   * validation for <code>ContCreateFlag</code>.
   */
  static class ContCreateFlagValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=contCreateFlag::validate
     //--AgentGen END
    }
  }

  /**
   * The <code>ImageIdValidator</code> implements the value
   * validation for <code>ImageId</code>.
   */
  static class ImageIdValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 1L) && (v <= 1000L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=imageId::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>ImageNameValidator</code> implements the value
   * validation for <code>ImageName</code>.
   */
  static class ImageNameValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=imageName::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>ImageVersValidator</code> implements the value
   * validation for <code>ImageVers</code>.
   */
  static class ImageVersValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=imageVers::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class ContImageTableEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=contImageTableEntry::RowMembers
     //--AgentGen END

    public ContImageTableEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=contImageTableEntry::RowConstructor
     //--AgentGen END
    }
    
    public Integer32 getImageId() {
     //--AgentGen BEGIN=contImageTableEntry::getImageId
     //--AgentGen END
      return (Integer32) super.getValue(idxImageId);
    }  
    
    public void setImageId(Integer32 newValue) {
     //--AgentGen BEGIN=contImageTableEntry::setImageId
     //--AgentGen END
      super.setValue(idxImageId, newValue);
    }
    
    public OctetString getImageName() {
     //--AgentGen BEGIN=contImageTableEntry::getImageName
     //--AgentGen END
      return (OctetString) super.getValue(idxImageName);
    }  
    
    public void setImageName(OctetString newValue) {
     //--AgentGen BEGIN=contImageTableEntry::setImageName
     //--AgentGen END
      super.setValue(idxImageName, newValue);
    }
    
    public OctetString getImageVers() {
     //--AgentGen BEGIN=contImageTableEntry::getImageVers
     //--AgentGen END
      return (OctetString) super.getValue(idxImageVers);
    }  
    
    public void setImageVers(OctetString newValue) {
     //--AgentGen BEGIN=contImageTableEntry::setImageVers
     //--AgentGen END
      super.setValue(idxImageVers, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=contImageTableEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxImageId: 
        	return getImageId();
        case idxImageName: 
        	return getImageName();
        case idxImageVers: 
        	return getImageVers();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=contImageTableEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxImageId: 
        	setImageId((Integer32)value);
        	break;
        case idxImageName: 
        	setImageName((OctetString)value);
        	break;
        case idxImageVers: 
        	setImageVers((OctetString)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=contImageTableEntry::Row
     //--AgentGen END
  }
  
  class ContImageTableEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      ContImageTableEntryRow row = 
        new ContImageTableEntryRow(index, values);
     //--AgentGen BEGIN=contImageTableEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=contImageTableEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=contImageTableEntry::RowFactory
     //--AgentGen END
  }

  public class ContainerTableEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=containerTableEntry::RowMembers
     //--AgentGen END

    public ContainerTableEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=containerTableEntry::RowConstructor
     //--AgentGen END
    }
    
    public Integer32 getContainerId() {
     //--AgentGen BEGIN=containerTableEntry::getContainerId
     //--AgentGen END
      return (Integer32) super.getValue(idxContainerId);
    }  
    
    public void setContainerId(Integer32 newValue) {
     //--AgentGen BEGIN=containerTableEntry::setContainerId
     //--AgentGen END
      super.setValue(idxContainerId, newValue);
    }
    
    public OctetString getContainerName() {
     //--AgentGen BEGIN=containerTableEntry::getContainerName
     //--AgentGen END
      return (OctetString) super.getValue(idxContainerName);
    }  
    
    public void setContainerName(OctetString newValue) {
     //--AgentGen BEGIN=containerTableEntry::setContainerName
     //--AgentGen END
      super.setValue(idxContainerName, newValue);
    }
    
    public Integer32 getContainerImgId() {
     //--AgentGen BEGIN=containerTableEntry::getContainerImgId
     //--AgentGen END
      return (Integer32) super.getValue(idxContainerImgId);
    }  
    
    public void setContainerImgId(Integer32 newValue) {
     //--AgentGen BEGIN=containerTableEntry::setContainerImgId
     //--AgentGen END
      super.setValue(idxContainerImgId, newValue);
    }
    
    public OctetString getContainerStatus() {
     //--AgentGen BEGIN=containerTableEntry::getContainerStatus
     //--AgentGen END
      return (OctetString) super.getValue(idxContainerStatus);
    }  
    
    public void setContainerStatus(OctetString newValue) {
     //--AgentGen BEGIN=containerTableEntry::setContainerStatus
     //--AgentGen END
      super.setValue(idxContainerStatus, newValue);
    }
    
    public Integer32 getContainerProcUse() {
     //--AgentGen BEGIN=containerTableEntry::getContainerProcUse
     //--AgentGen END
      return (Integer32) super.getValue(idxContainerProcUse);
    }  
    
    public void setContainerProcUse(Integer32 newValue) {
     //--AgentGen BEGIN=containerTableEntry::setContainerProcUse
     //--AgentGen END
      super.setValue(idxContainerProcUse, newValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=containerTableEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxContainerId: 
        	return getContainerId();
        case idxContainerName: 
        	return getContainerName();
        case idxContainerImgId: 
        	return getContainerImgId();
        case idxContainerStatus: 
        	return getContainerStatus();
        case idxContainerProcUse: 
        	return getContainerProcUse();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=containerTableEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxContainerId: 
        	setContainerId((Integer32)value);
        	break;
        case idxContainerName: 
        	setContainerName((OctetString)value);
        	break;
        case idxContainerImgId: 
        	setContainerImgId((Integer32)value);
        	break;
        case idxContainerStatus: 
        	setContainerStatus((OctetString)value);
        	break;
        case idxContainerProcUse: 
        	setContainerProcUse((Integer32)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=containerTableEntry::Row
     //--AgentGen END
  }
  
  class ContainerTableEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      ContainerTableEntryRow row = 
        new ContainerTableEntryRow(index, values);
     //--AgentGen BEGIN=containerTableEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=containerTableEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=containerTableEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module CONTAINERSHIPv2-MIB
  protected void addTCsToFactory(MOFactory moFactory) {
   moFactory.addTextualConvention(new CreateFlag()); 
  }


  public class CreateFlag implements TextualConvention {
    public static final int noCreate = 0;
    public static final int create = 1;
  	
    public CreateFlag() {
    }

    public String getModuleName() {
      return TC_MODULE_CONTAINERSHIPV2_MIB;
    }
  	
    public String getName() {
      return TC_CREATEFLAG;
    }
    
    public Variable createInitialValue() {
    	Variable v = new Integer32();
      if (v instanceof AssignableFromLong) {
        ((AssignableFromLong)v).setValue(0);
      }
    	// further modify value to comply with TC constraints here:
     //--AgentGen BEGIN=CreateFlag::createInitialValue
     //--AgentGen END
	    return v;
    }
  	
    public MOScalar createScalar(OID oid, MOAccess access, Variable value) {
      MOScalar scalar = moFactory.createScalar(oid, access, value);
      ValueConstraint vc = new EnumerationConstraint(
        new int[] { noCreate,
                    create });
      scalar.addMOValueValidationListener(new ValueConstraintValidator(vc));                                  
     //--AgentGen BEGIN=CreateFlag::createScalar
     //--AgentGen END
      return scalar;
    }
  	
    public MOColumn createColumn(int columnID, int syntax, MOAccess access,
                                 Variable defaultValue, boolean mutableInService) {
      MOColumn col = moFactory.createColumn(columnID, syntax, access, 
                                            defaultValue, mutableInService);
      if (col instanceof MOMutableColumn) {
        MOMutableColumn mcol = (MOMutableColumn)col;
        ValueConstraint vc = new EnumerationConstraint(
          new int[] { noCreate,
                      create });
        mcol.addMOValueValidationListener(new ValueConstraintValidator(vc));                                  
      }
     //--AgentGen BEGIN=CreateFlag::createColumn
     //--AgentGen END
      return col;      
    }
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_BEGIN
//--AgentGen END

  // Textual Definitions of other MIB modules
  public void addImportedTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_END
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END
}


