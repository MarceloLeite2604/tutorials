// Code generated by protoc-gen-go. DO NOT EDIT.
// source: prime_numbers.proto

package primenumbersguide

import (
	context "context"
	fmt "fmt"
	proto "github.com/golang/protobuf/proto"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
	math "math"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.ProtoPackageIsVersion3 // please upgrade the proto package

type Number struct {
	Value                int32    `protobuf:"varint,1,opt,name=value,proto3" json:"value,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Number) Reset()         { *m = Number{} }
func (m *Number) String() string { return proto.CompactTextString(m) }
func (*Number) ProtoMessage()    {}
func (*Number) Descriptor() ([]byte, []int) {
	return fileDescriptor_1eb2e66992a58db7, []int{0}
}

func (m *Number) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Number.Unmarshal(m, b)
}
func (m *Number) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Number.Marshal(b, m, deterministic)
}
func (m *Number) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Number.Merge(m, src)
}
func (m *Number) XXX_Size() int {
	return xxx_messageInfo_Number.Size(m)
}
func (m *Number) XXX_DiscardUnknown() {
	xxx_messageInfo_Number.DiscardUnknown(m)
}

var xxx_messageInfo_Number proto.InternalMessageInfo

func (m *Number) GetValue() int32 {
	if m != nil {
		return m.Value
	}
	return 0
}

type Boolean struct {
	Value                bool     `protobuf:"varint,1,opt,name=value,proto3" json:"value,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Boolean) Reset()         { *m = Boolean{} }
func (m *Boolean) String() string { return proto.CompactTextString(m) }
func (*Boolean) ProtoMessage()    {}
func (*Boolean) Descriptor() ([]byte, []int) {
	return fileDescriptor_1eb2e66992a58db7, []int{1}
}

func (m *Boolean) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Boolean.Unmarshal(m, b)
}
func (m *Boolean) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Boolean.Marshal(b, m, deterministic)
}
func (m *Boolean) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Boolean.Merge(m, src)
}
func (m *Boolean) XXX_Size() int {
	return xxx_messageInfo_Boolean.Size(m)
}
func (m *Boolean) XXX_DiscardUnknown() {
	xxx_messageInfo_Boolean.DiscardUnknown(m)
}

var xxx_messageInfo_Boolean proto.InternalMessageInfo

func (m *Boolean) GetValue() bool {
	if m != nil {
		return m.Value
	}
	return false
}

func init() {
	proto.RegisterType((*Number)(nil), "primenumbers.Number")
	proto.RegisterType((*Boolean)(nil), "primenumbers.Boolean")
}

func init() { proto.RegisterFile("prime_numbers.proto", fileDescriptor_1eb2e66992a58db7) }

var fileDescriptor_1eb2e66992a58db7 = []byte{
	// 247 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0xe2, 0x12, 0x2e, 0x28, 0xca, 0xcc,
	0x4d, 0x8d, 0xcf, 0x2b, 0xcd, 0x4d, 0x4a, 0x2d, 0x2a, 0xd6, 0x2b, 0x28, 0xca, 0x2f, 0xc9, 0x17,
	0xe2, 0x01, 0x0b, 0x42, 0xc5, 0x94, 0xe4, 0xb8, 0xd8, 0xfc, 0xc0, 0x4c, 0x21, 0x11, 0x2e, 0xd6,
	0xb2, 0xc4, 0x9c, 0xd2, 0x54, 0x09, 0x46, 0x05, 0x46, 0x0d, 0xd6, 0x20, 0x08, 0x47, 0x49, 0x9e,
	0x8b, 0xdd, 0x29, 0x3f, 0x3f, 0x27, 0x35, 0x31, 0x0f, 0x55, 0x01, 0x07, 0x54, 0x81, 0xd1, 0x04,
	0x26, 0x2e, 0x9e, 0x00, 0x90, 0x89, 0x10, 0x63, 0x8a, 0x85, 0x2c, 0xb8, 0xd8, 0x3d, 0x8b, 0xc1,
	0x22, 0x42, 0x22, 0x7a, 0xc8, 0x76, 0xe9, 0x41, 0x54, 0x48, 0x89, 0xa2, 0x8a, 0x42, 0x8d, 0x57,
	0x62, 0x10, 0x72, 0xe0, 0xe2, 0x73, 0xcc, 0xc9, 0x01, 0x6b, 0x2d, 0x0e, 0xcd, 0x2b, 0xc9, 0xcc,
	0xc1, 0x61, 0x00, 0x56, 0x51, 0x25, 0x06, 0x03, 0x46, 0x21, 0x7b, 0x2e, 0x1e, 0xc7, 0xa2, 0x54,
	0xb8, 0x21, 0x24, 0x3a, 0x40, 0x83, 0x51, 0xc8, 0x99, 0x8b, 0x2f, 0x3c, 0x23, 0x33, 0x39, 0xc3,
	0xb1, 0x28, 0x95, 0x4c, 0x23, 0x0c, 0x18, 0x9d, 0x02, 0xb9, 0xac, 0x92, 0x8a, 0xf4, 0x92, 0xf3,
	0x73, 0xf5, 0xd2, 0x33, 0x4b, 0x32, 0x4a, 0x93, 0xf4, 0x72, 0x13, 0x8b, 0x92, 0x53, 0x73, 0xf2,
	0x73, 0x52, 0x33, 0x4b, 0x52, 0x8d, 0xcc, 0x0c, 0x4c, 0xf4, 0x4a, 0x4a, 0x4b, 0xf2, 0x8b, 0x32,
	0x13, 0x73, 0x8a, 0xf5, 0xd2, 0x8b, 0x0a, 0x92, 0x51, 0xcc, 0x72, 0x12, 0x44, 0x0e, 0xcd, 0x00,
	0x50, 0x94, 0x05, 0x30, 0x26, 0xb1, 0x81, 0xe3, 0xce, 0x18, 0x10, 0x00, 0x00, 0xff, 0xff, 0xd5,
	0x44, 0x40, 0x3a, 0xd2, 0x01, 0x00, 0x00,
}

// Reference imports to suppress errors if they are not otherwise used.
var _ context.Context
var _ grpc.ClientConn

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
const _ = grpc.SupportPackageIsVersion4

// PrimeNumbersClient is the client API for PrimeNumbers service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://godoc.org/google.golang.org/grpc#ClientConn.NewStream.
type PrimeNumbersClient interface {
	IsPrime(ctx context.Context, in *Number, opts ...grpc.CallOption) (*Boolean, error)
	AllPrimesUntil(ctx context.Context, in *Number, opts ...grpc.CallOption) (PrimeNumbers_AllPrimesUntilClient, error)
	AreAllPrimes(ctx context.Context, opts ...grpc.CallOption) (PrimeNumbers_AreAllPrimesClient, error)
	WhichArePrimes(ctx context.Context, opts ...grpc.CallOption) (PrimeNumbers_WhichArePrimesClient, error)
}

type primeNumbersClient struct {
	cc *grpc.ClientConn
}

func NewPrimeNumbersClient(cc *grpc.ClientConn) PrimeNumbersClient {
	return &primeNumbersClient{cc}
}

func (c *primeNumbersClient) IsPrime(ctx context.Context, in *Number, opts ...grpc.CallOption) (*Boolean, error) {
	out := new(Boolean)
	err := c.cc.Invoke(ctx, "/primenumbers.PrimeNumbers/IsPrime", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *primeNumbersClient) AllPrimesUntil(ctx context.Context, in *Number, opts ...grpc.CallOption) (PrimeNumbers_AllPrimesUntilClient, error) {
	stream, err := c.cc.NewStream(ctx, &_PrimeNumbers_serviceDesc.Streams[0], "/primenumbers.PrimeNumbers/AllPrimesUntil", opts...)
	if err != nil {
		return nil, err
	}
	x := &primeNumbersAllPrimesUntilClient{stream}
	if err := x.ClientStream.SendMsg(in); err != nil {
		return nil, err
	}
	if err := x.ClientStream.CloseSend(); err != nil {
		return nil, err
	}
	return x, nil
}

type PrimeNumbers_AllPrimesUntilClient interface {
	Recv() (*Number, error)
	grpc.ClientStream
}

type primeNumbersAllPrimesUntilClient struct {
	grpc.ClientStream
}

func (x *primeNumbersAllPrimesUntilClient) Recv() (*Number, error) {
	m := new(Number)
	if err := x.ClientStream.RecvMsg(m); err != nil {
		return nil, err
	}
	return m, nil
}

func (c *primeNumbersClient) AreAllPrimes(ctx context.Context, opts ...grpc.CallOption) (PrimeNumbers_AreAllPrimesClient, error) {
	stream, err := c.cc.NewStream(ctx, &_PrimeNumbers_serviceDesc.Streams[1], "/primenumbers.PrimeNumbers/AreAllPrimes", opts...)
	if err != nil {
		return nil, err
	}
	x := &primeNumbersAreAllPrimesClient{stream}
	return x, nil
}

type PrimeNumbers_AreAllPrimesClient interface {
	Send(*Number) error
	CloseAndRecv() (*Boolean, error)
	grpc.ClientStream
}

type primeNumbersAreAllPrimesClient struct {
	grpc.ClientStream
}

func (x *primeNumbersAreAllPrimesClient) Send(m *Number) error {
	return x.ClientStream.SendMsg(m)
}

func (x *primeNumbersAreAllPrimesClient) CloseAndRecv() (*Boolean, error) {
	if err := x.ClientStream.CloseSend(); err != nil {
		return nil, err
	}
	m := new(Boolean)
	if err := x.ClientStream.RecvMsg(m); err != nil {
		return nil, err
	}
	return m, nil
}

func (c *primeNumbersClient) WhichArePrimes(ctx context.Context, opts ...grpc.CallOption) (PrimeNumbers_WhichArePrimesClient, error) {
	stream, err := c.cc.NewStream(ctx, &_PrimeNumbers_serviceDesc.Streams[2], "/primenumbers.PrimeNumbers/WhichArePrimes", opts...)
	if err != nil {
		return nil, err
	}
	x := &primeNumbersWhichArePrimesClient{stream}
	return x, nil
}

type PrimeNumbers_WhichArePrimesClient interface {
	Send(*Number) error
	Recv() (*Boolean, error)
	grpc.ClientStream
}

type primeNumbersWhichArePrimesClient struct {
	grpc.ClientStream
}

func (x *primeNumbersWhichArePrimesClient) Send(m *Number) error {
	return x.ClientStream.SendMsg(m)
}

func (x *primeNumbersWhichArePrimesClient) Recv() (*Boolean, error) {
	m := new(Boolean)
	if err := x.ClientStream.RecvMsg(m); err != nil {
		return nil, err
	}
	return m, nil
}

// PrimeNumbersServer is the server API for PrimeNumbers service.
type PrimeNumbersServer interface {
	IsPrime(context.Context, *Number) (*Boolean, error)
	AllPrimesUntil(*Number, PrimeNumbers_AllPrimesUntilServer) error
	AreAllPrimes(PrimeNumbers_AreAllPrimesServer) error
	WhichArePrimes(PrimeNumbers_WhichArePrimesServer) error
}

// UnimplementedPrimeNumbersServer can be embedded to have forward compatible implementations.
type UnimplementedPrimeNumbersServer struct {
}

func (*UnimplementedPrimeNumbersServer) IsPrime(ctx context.Context, req *Number) (*Boolean, error) {
	return nil, status.Errorf(codes.Unimplemented, "method IsPrime not implemented")
}
func (*UnimplementedPrimeNumbersServer) AllPrimesUntil(req *Number, srv PrimeNumbers_AllPrimesUntilServer) error {
	return status.Errorf(codes.Unimplemented, "method AllPrimesUntil not implemented")
}
func (*UnimplementedPrimeNumbersServer) AreAllPrimes(srv PrimeNumbers_AreAllPrimesServer) error {
	return status.Errorf(codes.Unimplemented, "method AreAllPrimes not implemented")
}
func (*UnimplementedPrimeNumbersServer) WhichArePrimes(srv PrimeNumbers_WhichArePrimesServer) error {
	return status.Errorf(codes.Unimplemented, "method WhichArePrimes not implemented")
}

func RegisterPrimeNumbersServer(s *grpc.Server, srv PrimeNumbersServer) {
	s.RegisterService(&_PrimeNumbers_serviceDesc, srv)
}

func _PrimeNumbers_IsPrime_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(Number)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(PrimeNumbersServer).IsPrime(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/primenumbers.PrimeNumbers/IsPrime",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(PrimeNumbersServer).IsPrime(ctx, req.(*Number))
	}
	return interceptor(ctx, in, info, handler)
}

func _PrimeNumbers_AllPrimesUntil_Handler(srv interface{}, stream grpc.ServerStream) error {
	m := new(Number)
	if err := stream.RecvMsg(m); err != nil {
		return err
	}
	return srv.(PrimeNumbersServer).AllPrimesUntil(m, &primeNumbersAllPrimesUntilServer{stream})
}

type PrimeNumbers_AllPrimesUntilServer interface {
	Send(*Number) error
	grpc.ServerStream
}

type primeNumbersAllPrimesUntilServer struct {
	grpc.ServerStream
}

func (x *primeNumbersAllPrimesUntilServer) Send(m *Number) error {
	return x.ServerStream.SendMsg(m)
}

func _PrimeNumbers_AreAllPrimes_Handler(srv interface{}, stream grpc.ServerStream) error {
	return srv.(PrimeNumbersServer).AreAllPrimes(&primeNumbersAreAllPrimesServer{stream})
}

type PrimeNumbers_AreAllPrimesServer interface {
	SendAndClose(*Boolean) error
	Recv() (*Number, error)
	grpc.ServerStream
}

type primeNumbersAreAllPrimesServer struct {
	grpc.ServerStream
}

func (x *primeNumbersAreAllPrimesServer) SendAndClose(m *Boolean) error {
	return x.ServerStream.SendMsg(m)
}

func (x *primeNumbersAreAllPrimesServer) Recv() (*Number, error) {
	m := new(Number)
	if err := x.ServerStream.RecvMsg(m); err != nil {
		return nil, err
	}
	return m, nil
}

func _PrimeNumbers_WhichArePrimes_Handler(srv interface{}, stream grpc.ServerStream) error {
	return srv.(PrimeNumbersServer).WhichArePrimes(&primeNumbersWhichArePrimesServer{stream})
}

type PrimeNumbers_WhichArePrimesServer interface {
	Send(*Boolean) error
	Recv() (*Number, error)
	grpc.ServerStream
}

type primeNumbersWhichArePrimesServer struct {
	grpc.ServerStream
}

func (x *primeNumbersWhichArePrimesServer) Send(m *Boolean) error {
	return x.ServerStream.SendMsg(m)
}

func (x *primeNumbersWhichArePrimesServer) Recv() (*Number, error) {
	m := new(Number)
	if err := x.ServerStream.RecvMsg(m); err != nil {
		return nil, err
	}
	return m, nil
}

var _PrimeNumbers_serviceDesc = grpc.ServiceDesc{
	ServiceName: "primenumbers.PrimeNumbers",
	HandlerType: (*PrimeNumbersServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "IsPrime",
			Handler:    _PrimeNumbers_IsPrime_Handler,
		},
	},
	Streams: []grpc.StreamDesc{
		{
			StreamName:    "AllPrimesUntil",
			Handler:       _PrimeNumbers_AllPrimesUntil_Handler,
			ServerStreams: true,
		},
		{
			StreamName:    "AreAllPrimes",
			Handler:       _PrimeNumbers_AreAllPrimes_Handler,
			ClientStreams: true,
		},
		{
			StreamName:    "WhichArePrimes",
			Handler:       _PrimeNumbers_WhichArePrimes_Handler,
			ServerStreams: true,
			ClientStreams: true,
		},
	},
	Metadata: "prime_numbers.proto",
}
